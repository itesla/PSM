package org.power_systems_modelica.psm.ddr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import org.power_systems_modelica.psm.ddr.dyd.Component;
import org.power_systems_modelica.psm.ddr.dyd.Connection;
import org.power_systems_modelica.psm.ddr.dyd.Connector;
import org.power_systems_modelica.psm.ddr.dyd.Model;
import org.power_systems_modelica.psm.ddr.dyd.ModelForType;
import org.power_systems_modelica.psm.ddr.dyd.ModelProvider;
import org.power_systems_modelica.psm.ddr.dyd.Parameter;
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
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
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
		// We will try to infer,
		// for every dynamic model declaration and equation seen in the Modelica file,
		// the corresponding element from the static model.

		// We will discard connection equations that refer to distinct static elements
		// because we assume they are built from Network topology and are not required
		// in the dynamic data repository.

		// Obtain the Modelica objects from the MO file
		ModelicaDocument mo = new ModelicaParser().parse(mofile);

		// Try to group all dynamic model components that refer to the same static element
		Collection<ModelicaModel> mos = groupInModelsByStaticId(mo);

		// Build the dynamic repository objects
		ModelProvider dyd = new ModelProvider();
		ParameterSetContainer par = new ParameterSetContainer();
		par.setFilename(parname);
		mo2dyd(mos, dyd, par);

		// Save the dynamic repository objects as xml files
		Path dydf = ddrloc.resolve(dydname);
		Path parf = ddrloc.resolve(parname);
		ModelContainerXml.write(dydf, dyd.getDefaultContainer());
		ParameterSetContainerXml.write(parf, par);
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
		String type = whichType(mo);
		if (type == null) return null;

		Model mdef = dyd.getDynamicModelForStaticType(type);
		if (mdef != null) return mdef;

		// Do not build generic model definition for generators
		if (type.equals("Generator")) return null;

		return buildModelDefinitionForType(type, mo, dyd, par);
	}

	private static String whichType(ModelicaModel mo)
	{
		ModelicaDeclaration d = mo.getDeclarations().get(0);
		String dtype = d.getType();
		String stype = ModelicaTricks.getStaticTypeFromDynamicType(dtype);
		return stype;
	}

	private static Model buildModelDefinitionForType(
			String stype,
			ModelicaModel mo,
			ModelProvider dyd,
			ParameterSetContainer par)
	{
		Model mdef = new ModelForType(stype);
		boolean generic = true;
		mdef.addConnectors(createConnectors(mo, generic));

		// We only process the first (unique) declaration
		ModelicaDeclaration d = mo.getDeclarations().get(0);

		ParameterSet pset = par.newParameterSet();
		pset.add(buildParameters(stype, d.getArguments()));
		Component mdefc = new Component(null, d.getType());
		mdefc.setParameterSet(pset);
		mdef.addComponent(mdefc);

		dyd.add(mdef);
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
			String reason = "staticId is empty and we are trying to create a DYD item for a specific element";
			LOG.warn("Ignored ModelicaModel {}: {}", mo.getName(), reason);
			return null;
		}
		String type = whichType(mo);

		Model mdef = new Model(id, staticId);
		boolean generic = false;
		mdef.addConnectors(createConnectors(mo, generic));

		for (ModelicaDeclaration d : mo.getDeclarations())
		{
			String idc = d.getId();
			String name = d.getType();
			Component mdefc = new Component(idc, name);
			if (d.getArguments() != null && !d.getArguments().isEmpty())
			{
				ParameterSet pset = par.newParameterSet();
				pset.add(buildParameters(type, d.getArguments()));
				par.add(pset);
				ParameterSetReference pref = new ParameterSetReference(
						par.getFilename(),
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

	private static List<Parameter> buildParameters(String stype, List<ModelicaArgument> arguments)
	{
		List<Parameter> params = new ArrayList<>(arguments.size());
		for (ModelicaArgument a : arguments)
		{
			String iidmAttribute = IidmNames.getIidmNameForModelicaArgument(stype, a.getName());
			if (iidmAttribute != null)
				params.add(new ParameterReference(a.getName(), "IIDM", iidmAttribute));
			else
				params.add(new ParameterValue("STRING", a.getName(), a.getValue()));
		}
		return params;
	}

	private static List<Connector> createConnectors(ModelicaModel mo, boolean generic)
	{
		String name = mo.getDeclarations().get(0).getId();
		boolean isBranch = name.startsWith("line_")
				|| name.startsWith("trafo_")
				|| name.startsWith("Line_")
				|| name.startsWith("Transformer");
		boolean isGenerator = name.startsWith("gen_");
		boolean isBus = name.startsWith("bus_")
				|| name.startsWith("Bus_");

		// Only branches have two connectors
		Connector[] connectors = new Connector[isBranch ? 2 : 1];

		String id = generic ? null : name;
		String pin;
		boolean reusable;

		// Connector 1
		pin = "p";
		if (isGenerator) pin = "sortie";
		reusable = false;
		if (isBus) reusable = true;
		connectors[0] = new Connector(id, pin, reusable);

		// Connector 2
		if (isBranch)
		{
			pin = "n";
			reusable = false;
			connectors[1] = new Connector(id, pin, reusable);
		}

		return Arrays.asList(connectors);
	}

	private static Collection<ModelicaModel> groupInModelsByStaticId(ModelicaDocument mo)
	{
		Map<String, ModelicaModel> models = new HashMap<>();

		List<ModelicaDeclaration> ds = mo.getSystemModel().getDeclarations();
		List<ModelicaEquation> eqs = mo.getSystemModel().getEquations();
		for (ModelicaDeclaration d : ds)
		{
			ModelicaModel m = putget(models, whichModel(d));
			if (m != null) m.addDeclarations(Arrays.asList(d));
			else
			{
				LOG.warn("ignored declaration {}", d.getId());
			}
		}
		for (ModelicaEquation eq : eqs)
		{
			ModelicaModel m = putget(models, whichModel(eq));
			if (m != null) m.addEquations(Arrays.asList(eq));
			else
			{
				String reason = "connects between different model components are not captured as dynamic model definitions";
				LOG.debug("ignored equation '{}', {}", eq.toString(), reason);
			}
		}
		return models.values();
	}

	private static String whichModel(ModelicaDeclaration d)
	{
		String m = whichModelFromAnnotation(d.getAnnotation());
		if (m == null) m = whichModelFromName(d.getId());
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
