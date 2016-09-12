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

import org.power_systems_modelica.psm.ddr.dyd.Connection;
import org.power_systems_modelica.psm.ddr.dyd.Component;
import org.power_systems_modelica.psm.ddr.dyd.Model;
import org.power_systems_modelica.psm.ddr.dyd.ModelContainer;
import org.power_systems_modelica.psm.ddr.dyd.ParameterValue;
import org.power_systems_modelica.psm.ddr.dyd.ParameterSet;
import org.power_systems_modelica.psm.ddr.dyd.ParameterSetContainer;
import org.power_systems_modelica.psm.ddr.dyd.ParameterSetReference;
import org.power_systems_modelica.psm.ddr.dyd.xml.ModelContainerXml;
import org.power_systems_modelica.psm.ddr.dyd.xml.ParameterSetContainerXml;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
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
		ModelContainer dyd = new ModelContainer();
		ParameterSetContainer par = new ParameterSetContainer();
		par.setFilename(parname);
		mo2dyd(mos, dyd, par);
		ModelContainerXml.write(ddrloc.resolve(dydname), dyd);
		ParameterSetContainerXml.write(ddrloc.resolve(parname), par);
	}

	public static void mo2dyd(
			Collection<ModelicaModel> mos,
			ModelContainer dyd,
			ParameterSetContainer par)
	{
		int psetCounter = 0;
		String psetContainer = par.getFilename();

		for (ModelicaModel mo : mos)
		{
			String id = mo.getName();
			String staticId = mo.getStaticId();
			if (staticId == null)
			{
				String reason = "staticId is empty, only know how to create dyd for specific elements";
				LOG.warn("Ignored ModelicaModel {}: {}", mo.getName(), reason);
				continue;
			}
			Model mdef = new Model(id, staticId);
			for (ModelicaModelInstantiation mi : mo.getModelInstantiations())
			{
				String idc = mi.getName();
				String name = mi.getType();
				Component mdefc = new Component(idc, name);
				if (mi.getArguments() != null && !mi.getArguments().isEmpty())
				{
					++psetCounter;
					String psetId = "" + psetCounter;
					ParameterSet pset = new ParameterSet(psetId);
					for (ModelicaArgument a : mi.getArguments())
						pset.add(new ParameterValue("STRING", a.getName(), a.getValue()));
					par.add(pset);
					ParameterSetReference pref = new ParameterSetReference(psetContainer, psetId);
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
			dyd.add(mdef);
		}
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
