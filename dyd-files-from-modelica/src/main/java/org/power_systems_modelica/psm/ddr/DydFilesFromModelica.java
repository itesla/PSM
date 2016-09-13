package org.power_systems_modelica.psm.ddr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import org.power_systems_modelica.psm.ddr.dyd.Component;
import org.power_systems_modelica.psm.ddr.dyd.Connection;
import org.power_systems_modelica.psm.ddr.dyd.Model;
import org.power_systems_modelica.psm.ddr.dyd.ModelForType;
import org.power_systems_modelica.psm.ddr.dyd.ModelProvider;
import org.power_systems_modelica.psm.ddr.dyd.ParameterReference;
import org.power_systems_modelica.psm.ddr.dyd.ParameterSet;
import org.power_systems_modelica.psm.ddr.dyd.ParameterSetContainer;
import org.power_systems_modelica.psm.ddr.dyd.ParameterSetReference;
import org.power_systems_modelica.psm.ddr.dyd.ParameterValue;
import org.power_systems_modelica.psm.ddr.dyd.xml.ModelContainerXml;
import org.power_systems_modelica.psm.ddr.dyd.xml.ParameterSetContainerXml;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaModelInstantiation;
import org.power_systems_modelica.psm.modelica.ModelicaTricks;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DydFilesFromModelica
{
	public static void mo2dyd(Path mofile, Path ddrloc, String dydname, String parname)
			throws FileNotFoundException, IOException, XMLStreamException
	{
		// We will try to infer, for every dynamic model instantiation and equation,
		// the corresponding element from the static model.
		// We will discard connection equations that refer to distinct static elements
		// because we assume they are built from Network topology and are not required
		// in the dynamic data repository.

		// Obtain the Modelica objects from the MO file
		ModelicaDocument mo = new ModelicaParser().parse(mofile);

		// Try to group all dynamic model components that refer to the same static element
		Collection<ModelicaModel> mos = groupInModelsByStaticId(mo);

		// Build the dynamic repository objects and output them as xml files
		ModelProvider dyd = new ModelProvider();
		ParameterSetContainer par = new ParameterSetContainer();
		par.setFilename(parname);
		mo2dyd(mos, dyd, par);
		ModelContainerXml.write(ddrloc.resolve(dydname), dyd.getDefaultContainer());
		ParameterSetContainerXml.write(ddrloc.resolve(parname), par);
	}

	private static void mo2dyd(
			Collection<ModelicaModel> mos,
			ModelProvider dyd,
			ParameterSetContainer par)
	{
		for (ModelicaModel mo : mos)
		{
			Model mdef = checkModelDefinitionForType(mo, dyd, par);
			if (mdef == null)
			{
				mdef = buildModelDefinitionForElement(mo, par);
				if (mdef != null)
				{
					dyd.add(mdef);
				}
			}
		}
	}

	private static Model checkModelDefinitionForType(
			ModelicaModel mo,
			ModelProvider dyd,
			ParameterSetContainer par)
	{
		// Only if the dynamic model has a single model instantiation with no equations
		if (mo.getModelInstantiations().size() > 1 || mo.getEquations().size() > 0) return null;

		ModelicaModelInstantiation mi = mo.getModelInstantiations().get(0); 
		String dtype = mi.getType();
		String stype = ModelicaTricks.getStaticTypeFromDynamicType(dtype);
		if (stype == null) return null;

		Model mdef = dyd.getDynamicModelForStaticType(stype);
		if (mdef != null) return mdef;

		System.out.println("MT checking dynamic model definition based on type");
		System.out.println("MT     dynamic type = " + dtype);
		System.out.println("MT     static type  = " + stype);
		System.out.println("MT     trying to make a model definition ...");

		mdef = new ModelForType(stype);
		
		// We only process the first (unique) model instantiation
		ParameterSet pset = par.newParameterSet();
		par.add(pset);
		ParameterSetReference pref = new ParameterSetReference(par.getFilename(), pset.getId());
		Component mdefc = new Component(null, mi.getType());
		mdefc.setParameterSetReference(pref);
		mdef.addComponent(mdefc);
		
		System.out.println("MT     These are the arguments");
		for (ModelicaArgument a : mo.getModelInstantiations().get(0).getArguments())
		{
			String iidmAttribute = getIidmNameForModelicaArgument(stype, a.getName());
			System.out.printf("MT         %-10s --> %-10s%n", a.getName(), iidmAttribute);
			if (iidmAttribute != null)
				pset.add(new ParameterReference(a.getName(), "IIDM", iidmAttribute));
			else pset.add(new ParameterValue("STRING", a.getName(), a.getValue()));
		}
		if (mdef != null) dyd.add(mdef);
		return mdef;
	}

	private static Model buildModelDefinitionForElement(
			ModelicaModel mo,
			ParameterSetContainer par)
	{
		String id = mo.getName();
		String staticId = mo.getStaticId();
		if (staticId == null)
		{
			String reason = "staticId is empty, only know how to create dyd for specific elements";
			LOG.warn("Ignored ModelicaModel {}: {}", mo.getName(), reason);
			return null;
		}

		Model mdef = new Model(id, staticId);
		for (ModelicaModelInstantiation mi : mo.getModelInstantiations())
		{
			String idc = mi.getName();
			String name = mi.getType();
			Component mdefc = new Component(idc, name);
			if (mi.getArguments() != null && !mi.getArguments().isEmpty())
			{
				ParameterSet pset = par.newParameterSet();
				for (ModelicaArgument a : mi.getArguments())
					pset.add(new ParameterValue("STRING", a.getName(), a.getValue()));
				par.add(pset);
				ParameterSetReference pref = new ParameterSetReference(par.getFilename(),
						pset.getId());
				mdefc.setParameterSetReference(pref);
			}
			mdef.addComponent(mdefc);
		}
		for (ModelicaEquation eq : mo.getEquations())
		{
			if (eq instanceof ModelicaConnect)
			{
				ModelicaConnect eqc = (ModelicaConnect) eq;
				String[] idvar1 = ModelicaUtil.ref2idvar(eqc.getRef1());
				String[] idvar2 = ModelicaUtil.ref2idvar(eqc.getRef2());
				String id1 = idvar1[0];
				String var1 = idvar1[1];
				String id2 = idvar2[0];
				String var2 = idvar2[1];
				mdef.addConnection(new Connection(id1, var1, id2, var2));
			}
		}
		return mdef;
	}

	private static String getIidmNameForModelicaArgument(String stype, String mname)
	{
		switch (stype)
		{
		case "Bus":
			switch (mname)
			{
			case "V_0":
				return "pu(V)";
			case "angle_0":
				return "rad(angle)";
			}
		case "Line":
			switch (mname)
			{
			case "R":
				return "pu(R)";
			case "X":
				return "pu(X)";
			case "G":
				return "pu(G1)";
			case "B":
				return "pu(B1)";
			}
		case "Load":
			switch (mname)
			{
			case "V_0":
				return "pu(V)";
			case "P_0":
				return "P0";
			case "Q_0":
				return "Q0";
			case "angle_0":
				return "rad(angle)";
			}
		case "Shunt":
			switch (mname)
			{
			case "B":
				return "bPerSection";
			case "nsteps":
				return "CurrentSectionCount";
			}
		case "Transformer":
			switch (mname)
			{
			case "r":
				return "ratio";
			case "G0":
				return "pu(G)";
			case "B0":
				return "pu(B)";
			case "R":
				return "pu(R)";
			case "X":
				return "pu(X)";
			}
		}
		return null;
	}

	private static Collection<ModelicaModel> groupInModelsByStaticId(ModelicaDocument mo)
	{
		Map<String, ModelicaModel> models = new HashMap<>();

		List<ModelicaModelInstantiation> mis = mo.getSystemModel().getModelInstantiations();
		List<ModelicaEquation> eqs = mo.getSystemModel().getEquations();
		for (ModelicaModelInstantiation mi : mis)
		{
			ModelicaModel m = putget(models, whichModel(mi));
			if (m != null) m.addModelInstantiations(Arrays.asList(mi));
			else
			{
				System.err.printf("ignored model instantiation %s%n", mi.getName());
			}
		}
		for (ModelicaEquation eq : eqs)
		{
			ModelicaModel m = putget(models, whichModel(eq));
			if (m != null) m.addEquations(Arrays.asList(eq));
			else
			{
				String reason = "connects between different model components are not captured as dynamic model definitions";
				LOG.warn("ignored equation '{}', {}", eq.toString(), reason);
			}
		}
		return models.values();
	}

	private static String whichModel(ModelicaModelInstantiation mi)
	{
		String m = whichModelFromAnnotation(mi.getAnnotation());
		if (m == null) m = whichModelFromName(mi.getName());
		return m;
	}

	private static String whichModel(ModelicaEquation eq)
	{
		if (eq instanceof ModelicaConnect)
		{
			ModelicaConnect eqc = (ModelicaConnect) eq;
			String m1 = whichModelFromName(ModelicaUtil.ref2idvar(eqc.getRef1())[0]);
			String m2 = whichModelFromName(ModelicaUtil.ref2idvar(eqc.getRef2())[0]);
			if (m1.equals(m2)) return m1;
		}
		return null;
	}

	private static String whichModelFromName(String name)
	{
		return ModelicaTricks.getModel(name);
	}

	private static String whichModelFromAnnotation(String annotation)
	{
		// TODO if (annotation.contains("ExternalReference")) return whichModelFromExternalReference();
		return null;
	}

	private static ModelicaModel putget(Map<String, ModelicaModel> models, String id)
	{
		if (id == null) return null;
		if (!models.containsKey(id))
		{
			String dmid = ModelicaUtil.dynamicIdFromStaticId(id);
			ModelicaModel m = new ModelicaModel(dmid);
			m.setStaticId(id);
			models.put(id, m);
		}
		return models.get(id);
	}

	private static final Logger LOG = LoggerFactory.getLogger(DydFilesFromModelica.class);
}
