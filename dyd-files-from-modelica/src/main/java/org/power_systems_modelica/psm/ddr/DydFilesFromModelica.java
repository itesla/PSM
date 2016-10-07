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
import org.power_systems_modelica.psm.ddr.dyd.DynamicDataRepositoryDydFiles;
import org.power_systems_modelica.psm.ddr.dyd.Model;
import org.power_systems_modelica.psm.ddr.dyd.ModelForElement;
import org.power_systems_modelica.psm.ddr.dyd.ModelForType;
import org.power_systems_modelica.psm.ddr.dyd.Parameter;
import org.power_systems_modelica.psm.ddr.dyd.ParameterReference;
import org.power_systems_modelica.psm.ddr.dyd.ParameterSet;
import org.power_systems_modelica.psm.ddr.dyd.ParameterSetContainer;
import org.power_systems_modelica.psm.ddr.dyd.ParameterSetReference;
import org.power_systems_modelica.psm.ddr.dyd.ParameterValue;
import org.power_systems_modelica.psm.ddr.dyd.equations.UnparsedEquation;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaTricks;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;
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
		// We remove the equations that are related to interconnections between static elements
		// (they are not needed in the DDR, they will be built from the given topology of the static network)
		Collection<ModelicaModel> ms = ModelicaUtil.groupByStaticId(mo).values();
		ms = ms.stream()
				.filter(m -> !ModelicaUtil.isInterconnection(m))
				.collect(Collectors.toList());

		// A way to store some values present in the original Modelica files and use them later as if they were initialization results
		ModelicaSimulationResults fakeInitializationResults = new ModelicaSimulationResults();

		DynamicDataRepositoryDydFiles ddr = new DynamicDataRepositoryDydFiles();
		ddr.setLocation(ddrLocation.toString());
		ddr.addParameterSetContainer(PARAMS_NAME);

		mo2dyd(ms, ddr, fakeInitializationResults);

		// Save the dynamic repository objects as XML files, provide default names for the DYD containers
		ddr.write(SYSTEM_NAME, MODELS_NAME, INIT_NAME);

		// Save fake initialization results for later use
		Path fakef = ddrLocation.resolve(FAKE_INIT_NAME);
		ModelicaSimulationResultsCsv.write(fakef, fakeInitializationResults);
	}

	private static void mo2dyd(
			Collection<ModelicaModel> ms,
			DynamicDataRepositoryDydFiles ddr,
			ModelicaSimulationResults fakeInitResults)
	{
		// Keep track of the Model definitions added to the repository so they are not added twice
		Set<Model> addedModels = new HashSet<>();
		for (ModelicaModel m : ms)
		{
			if (ModelicaUtil.isSystemModel(m))
			{
				ddr.addSystemDeclarations(m.getDeclarations());
				// FIXME System equations are added 'as they appear' in the mo file, which additional considerations about write/read???
				ddr.addSystemEquations(m.getEquations()
						.stream()
						.map(meq -> new UnparsedEquation(meq.getText()))
						.collect(Collectors.toList()));
				continue;
			}

			Model mdef = checkModelDefinitionForType(m, ddr);
			if (mdef == null) mdef = buildModelDefinitionForElement(m, ddr, fakeInitResults);
			if (mdef == null) continue;
			if (addedModels.contains(mdef)) continue;

			ddr.addModel(mdef);
			addedModels.add(mdef);

			// TODO Infer proper initialization models.
			// Right now we are only changing names of components
			// taken from the dynamic model definition used for simulation
			// and storing them in a separate repository.
			Model mdefi = inferInitializationModel(mdef, ddr);
			if (mdefi != null) ddr.addInitializationModel(mdefi);
		}
	}

	private static Model inferInitializationModel(
			Model m,
			DynamicDataRepositoryDydFiles ddr)
	{
		// TODO We are doing it only for specific elements. Consider if we should do it for other sub-classes of Model
		if (!(m instanceof ModelForElement)) return null;
		ModelForElement m0 = (ModelForElement) m;

		ModelForElement mi = new ModelForElement(m0.getId(), m0.getStaticId());
		mi.addComponents(m.getComponents()
				.stream()
				.map(c -> inferInitializationComponent(c, ddr))
				.collect(Collectors.toList()));
		mi.addConnections(m.getConnections());
		mi.addConnectors(m.getConnectors());
		return mi;
	}

	private static Component inferInitializationComponent(
			Component c,
			DynamicDataRepositoryDydFiles ddr)
	{
		ParameterSetContainer par = ddr.getParameterSetContainer(PARAMS_NAME);

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
					par.getName(),
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
			ModelicaModel m,
			DynamicDataRepositoryDydFiles ddr)
	{
		String type = whichType(m);
		if (type == null) return null;

		Model mdef = ddr.getDynamicModelForStaticType(type);
		if (mdef != null) return mdef;

		// Do not build generic model definition for generators
		if (type.equals("Generator")) return null;

		return buildModelDefinitionForType(type, m, ddr);
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
			DynamicDataRepositoryDydFiles ddr)
	{
		Model mdef = new ModelForType(stype);
		boolean isGeneric = true;
		mdef.addConnectors(createConnectors(mo, isGeneric));

		// We only process the first (unique) declaration
		ModelicaDeclaration d = mo.getDeclarations().get(0);

		// A new parameter set for this model
		ParameterSetContainer par = ddr.getParameterSetContainer(PARAMS_NAME);
		ParameterSet pset = par.newParameterSet();
		pset.add(buildParameters(stype, d.getArguments()));
		Component mdefc = new Component(null, d.getType());
		mdefc.setParameterSet(pset);
		mdef.addComponent(mdefc);

		return mdef;
	}

	private static Model buildModelDefinitionForElement(
			ModelicaModel mo,
			DynamicDataRepositoryDydFiles ddr,
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
		ParameterSetContainer par = ddr.getParameterSetContainer(PARAMS_NAME);
		String type = whichType(mo);

		ModelForElement mdef = new ModelForElement(id, staticId);
		boolean isGeneric = false;
		mdef.addConnectors(createConnectors(mo, isGeneric));

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
						par.getName(),
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

	private static List<Connector> createConnectors(ModelicaModel m, boolean isGeneric)
	{
		String name = m.getDeclarations().get(0).getId();
		boolean isBranch = name.startsWith("line_")
				|| name.startsWith("trafo_")
				|| name.startsWith("Line_")
				|| name.startsWith("Transformer");
		boolean isGenerator = name.startsWith("gen_");
		boolean isBus = name.startsWith("bus_")
				|| name.startsWith("Bus_");

		// Only branches and generators have two connectors
		Connector[] connectors = new Connector[isBranch || isGenerator ? 2 : 1];

		// If the ModelicaModel is generic we do not have an id
		String id = isGeneric ? null : name;
		String pin;
		String target;

		// Connector 1
		pin = "p";
		if (isGenerator) pin = "sortie";
		if (isBus) target = null;
		else if (isBranch) target = "DYNN:bus1:p";
		else target = "DYNN:bus:p";
		connectors[0] = new Connector(id, pin, target);

		// Connector 2
		if (isBranch)
		{
			pin = "n";
			target = "DYNN:bus2:p";
			connectors[1] = new Connector(id, pin, target);
		}
		else if (isGenerator)
		{
			pin = "omegaRef";
			target = "DYNN:system:omegaRef";
			connectors[1] = new Connector(id, pin, target);
		}

		return Arrays.asList(connectors);
	}

	private static final String	PARAMS_NAME		= "params.par";
	private static final String	SYSTEM_NAME		= "system";
	private static final String	MODELS_NAME		= "models";
	private static final String	INIT_NAME		= "init";
	private static final String	FAKE_INIT_NAME	= "fake_init.csv";

	private static final Logger	LOG				= LoggerFactory
			.getLogger(DydFilesFromModelica.class);
}
