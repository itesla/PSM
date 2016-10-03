package org.power_systems_modelica.psm.ddr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.stream.XMLStreamException;

import org.power_systems_modelica.psm.ddr.dyd.Component;
import org.power_systems_modelica.psm.ddr.dyd.Connection;
import org.power_systems_modelica.psm.ddr.dyd.Connector;
import org.power_systems_modelica.psm.ddr.dyd.Model;
import org.power_systems_modelica.psm.ddr.dyd.ModelForElement;
import org.power_systems_modelica.psm.ddr.dyd.ModelForType;
import org.power_systems_modelica.psm.ddr.dyd.ModelProvider;
import org.power_systems_modelica.psm.ddr.dyd.Parameter;
import org.power_systems_modelica.psm.ddr.dyd.ParameterReference;
import org.power_systems_modelica.psm.ddr.dyd.ParameterSet;
import org.power_systems_modelica.psm.ddr.dyd.ParameterSetContainer;
import org.power_systems_modelica.psm.ddr.dyd.ParameterSetReference;
import org.power_systems_modelica.psm.ddr.dyd.ParameterValue;
import org.power_systems_modelica.psm.ddr.dyd.SystemDefinitions;
import org.power_systems_modelica.psm.ddr.dyd.equations.UnparsedEquation;
import org.power_systems_modelica.psm.ddr.dyd.xml.DydXml;
import org.power_systems_modelica.psm.ddr.dyd.xml.ParXml;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaTricks;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;
import org.power_systems_modelica.psm.modelica.builder.ModelicaBuilder;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationResults;
import org.power_systems_modelica.psm.modelica.engine.io.ModelicaSimulationResultsCsv;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DydFilesFromModelica
{
	public static void mo2dyd(Path modelicaFile, Path ddrLocation)
			throws FileNotFoundException, IOException, XMLStreamException
	{
		// We will try to infer,
		// for every dynamic model declaration and equation seen in the Modelica file,
		// the corresponding element from the static model.

		// We will discard connection equations that refer to distinct static elements
		// because we assume they are built from Network topology and are not required
		// in the dynamic data repository.

		// Obtain the Modelica objects from the MO file
		ModelicaDocument mo = new ModelicaParser().parse(modelicaFile);

		// Try to group all dynamic model components that refer to the same static element
		Collection<ModelicaModel> ms = ModelicaBuilder.groupByStaticId(mo);

		// Remove the equations that are related to interconnections between static elements
		// (they won't be part of the DDR)
		ms = ms.stream()
				.filter(m -> !ModelicaBuilder.isInterconnection(m))
				.collect(Collectors.toList());

		// FIXME simplify this simply creating a DDR and doing reset() on it (not connect), then referring it
		// Build the dynamic repository objects
		ModelProvider models = new ModelProvider(false);
		ModelProvider inits = new ModelProvider(true);
		ParameterSetContainer par = new ParameterSetContainer();
		par.setFilename("params.par");
		SystemDefinitions systemDefinitions = new SystemDefinitions();

		// A way to store some values present in the original Modelica files and use them later as if they were initialization results
		ModelicaSimulationResults fakeInitializationResults = new ModelicaSimulationResults();

		mo2dyd(ms, systemDefinitions, models, inits, par, fakeInitializationResults);

		// Save the dynamic repository objects as xml files
		Path dydModels = ddrLocation.resolve("models.dyd");
		Path dydInit = ddrLocation.resolve("init.dyd");
		Path parf = ddrLocation.resolve("params.par");
		Path dydSystem = ddrLocation.resolve("system.dyd");
		DydXml.write(dydSystem, systemDefinitions);
		DydXml.write(dydModels, models.getDefaultContainer());
		DydXml.write(dydInit, inits.getDefaultContainer());
		ParXml.write(parf, par);

		// Save fake initialization results
		Path fakef = ddrLocation.resolve("fake_init.csv");
		ModelicaSimulationResultsCsv.write(fakef, fakeInitializationResults);
	}

	private static void mo2dyd(
			Collection<ModelicaModel> ms,
			SystemDefinitions sys,
			ModelProvider dyd,
			ModelProvider dydInit,
			ParameterSetContainer par,
			ModelicaSimulationResults fakeInitResults)
	{
		// Keep track of the Model definitions added to the repository so they are not added twice
		Set<Model> addedModels = new HashSet<>();
		for (ModelicaModel m : ms)
		{
			if (ModelicaBuilder.isSystemModel(m))
			{
				sys.addDeclarations(m.getDeclarations());
				// FIXME System equations are added 'as they appear' in the mo file, which additional considerations about write/read???
				sys.addEquations(m.getEquations()
						.stream()
						.map(meq -> new UnparsedEquation(meq.getText()))
						.collect(Collectors.toList()));
				continue;
			}

			Model mdef = checkModelDefinitionForType(m, dyd, par);
			if (mdef == null) mdef = buildModelDefinitionForElement(m, par, fakeInitResults);
			if (mdef == null) continue;
			if (addedModels.contains(mdef)) continue;

			dyd.add(mdef);
			addedModels.add(mdef);

			// TODO Infer proper initialization models.
			// Right now we are only changing names of components
			// taken from the dynamic model definition used for simulation
			// and storing them in a separate repository.
			Model mdefi = inferInitializationModel(mdef, par);
			if (mdefi != null) dydInit.add(mdefi);
		}
	}

	private static Model inferInitializationModel(
			Model m,
			ParameterSetContainer par)
	{
		// TODO We are doing it only for specific elements. Consider if we should do it for other sub-classes of Model
		if (!(m instanceof ModelForElement)) return null;
		ModelForElement m0 = (ModelForElement) m;

		ModelForElement mi = new ModelForElement(m0.getId(), m0.getStaticId());
		mi.addComponents(m.getComponents()
				.stream()
				.map(c -> inferInitializationComponent(c, par))
				.collect(Collectors.toList()));
		mi.addConnections(m.getConnections());
		mi.addConnectors(m.getConnectors());
		return mi;
	}

	private static Component inferInitializationComponent(
			Component c,
			ParameterSetContainer par)
	{
		Component ci = new Component(c.getId(), c.getName() + "_Init");
		if (c.getParameterSet() != null)
		{
			ParameterSet psi = new ParameterSet(c.getParameterSet().getId());
			List<Parameter> params0 = c.getParameterSet().getParameters();
			psi.add(filterOutInitParameters(params0));
			ci.setParameterSet(psi);
		}
		else if (c.getParameterSetReference() != null)
		{
			ParameterSet psi = par.newParameterSet();
			par.add(psi);
			List<Parameter> params0 = par.get(c.getParameterSetReference().getSet())
					.getParameters();
			List<Parameter> params1 = filterOutInitParameters(params0);
			psi.add(params1);
			ParameterSetReference psir = new ParameterSetReference(
					par.getFilename(),
					psi.getId());
			ci.setParameterSetReference(psir);
		}
		return ci;
	}

	private static List<Parameter> filterOutInitParameters(List<Parameter> params)
	{
		return params.stream()
				.filter(p -> !(p.getName().startsWith("init_")))
				.collect(Collectors.toList());
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

		return mdef;
	}

	private static Model buildModelDefinitionForElement(
			ModelicaModel mo,
			ParameterSetContainer par,
			ModelicaSimulationResults fakeInitializationResults)
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

		ModelForElement mdef = new ModelForElement(id, staticId);
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

				// Store values of arguments coming from initialization for later use by fake Modelica engine
				saveInitializationResultsForFakeModelicaEngine(
						mdef,
						mdefc,
						d.getArguments(),
						fakeInitializationResults);
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

	private static void saveInitializationResultsForFakeModelicaEngine(
			ModelForElement m,
			Component c,
			List<ModelicaArgument> arguments,
			ModelicaSimulationResults fakeInitializationResults)
	{
		for (ModelicaArgument a : arguments)
		{
			Parameter p = checkInitializationReference(a);
			if (p != null)
			{
				fakeInitializationResults.addResult(
						m.getStaticId(),
						c.getId(),
						((ParameterReference) p).getSourceName(),
						a.getValue());
			}
		}
	}

	private static List<Parameter> buildParameters(String stype, List<ModelicaArgument> arguments)
	{
		List<Parameter> params = new ArrayList<>(arguments.size());
		for (ModelicaArgument a : arguments)
		{
			Parameter p = null;

			p = checkInitializationReference(a);
			if (p == null) p = checkIidmReference(stype, a);
			if (p == null) p = new ParameterValue("STRING", a.getName(), a.getValue());
			params.add(p);
		}
		return params;
	}

	private static Parameter checkInitializationReference(ModelicaArgument a)
	{
		if (a.getName().startsWith("init_"))
		{
			String sname = a.getName().replace("init_", "");
			return new ParameterReference(a.getName(), "INIT", sname);
		}
		return null;
	}

	private static Parameter checkIidmReference(String stype, ModelicaArgument a)
	{
		String iidmAttribute = IidmNames.getIidmNameForModelicaArgument(stype, a.getName());
		if (iidmAttribute != null)
			return new ParameterReference(a.getName(), "IIDM", iidmAttribute);
		return null;
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

	private static final Logger LOG = LoggerFactory.getLogger(DydFilesFromModelica.class);
}
