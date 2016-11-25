package org.power_systems_modelica.psm.ddr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.stream.XMLStreamException;

import org.power_systems_modelica.psm.ddr.dyd.Association;
import org.power_systems_modelica.psm.ddr.dyd.Component;
import org.power_systems_modelica.psm.ddr.dyd.Connection;
import org.power_systems_modelica.psm.ddr.dyd.Connector;
import org.power_systems_modelica.psm.ddr.dyd.DynamicDataRepositoryDydFiles;
import org.power_systems_modelica.psm.ddr.dyd.Model;
import org.power_systems_modelica.psm.ddr.dyd.ModelForAssociation;
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
	public void mo2dyd(
			Path modelicaFile,
			Path modelicaFileInit,
			Path ddrLocation)
			throws FileNotFoundException, IOException, XMLStreamException
	{
		// We will try to establish,
		// for every dynamic model declaration and equation seen in the Modelica file,
		// the corresponding element from the static model.

		// We will discard connection equations that refer to distinct static elements
		// because we assume they are built from Network topology and are not required
		// in the dynamic data repository.

		// Obtain the Modelica objects from the MO file
		ModelicaDocument mo = ModelicaParser.parse(modelicaFile);

		// Try to group all dynamic model components that refer to the same static element
		// We remove the equations that are related to interconnections between static elements
		// (they are not needed in the DDR, they will be built from the given topology of the static network)
		Collection<ModelicaModel> ms = ModelicaUtil.groupByNormalizedStaticId(mo).values();
		ms = ms.stream()
				.filter(m -> !ModelicaUtil.isInterconnection(m))
				.collect(Collectors.toList());

		// A way to store some values present in the original Modelica files and use them later as if they were initialization results
		ModelicaSimulationResults fakeInitializationResults = new ModelicaSimulationResults();

		DynamicDataRepositoryDydFiles ddr = new DynamicDataRepositoryDydFiles();
		ddr.setLocation(ddrLocation.toString());
		ddr.addParameterSetContainer(PARAMS_NAME);
		ddr.setSystemDefinitionsName(SYSTEM_NAME);

		boolean inferInitializationModels = (modelicaFileInit == null);
		boolean isInitializationModel = false;
		mo2dyd(ms,
				ddr,
				fakeInitializationResults,
				inferInitializationModels,
				isInitializationModel);
		if (modelicaFileInit != null)
		{
			ModelicaDocument moinit = ModelicaParser.parse(modelicaFileInit);
			Collection<ModelicaModel> msinit = ModelicaUtil
					.groupByNormalizedStaticId(moinit)
					.values();
			msinit = msinit.stream()
					.filter(m -> !ModelicaUtil.isInterconnection(m))
					.collect(Collectors.toList());
			isInitializationModel = true;
			mo2dyd(msinit,
					ddr,
					fakeInitializationResults,
					inferInitializationModels,
					isInitializationModel);
		}

		// Save the dynamic repository objects as XML files, provide default names for the DYD containers
		ddr.write();

		// Save fake initialization results for later use
		Path fakef = ddrLocation.resolve(FAKE_INIT_NAME);
		ModelicaSimulationResultsCsv.write(fakef, fakeInitializationResults);
	}

	private void mo2dyd(
			Collection<ModelicaModel> ms,
			DynamicDataRepositoryDydFiles ddr,
			ModelicaSimulationResults fakeInitResults,
			boolean inferInitializationModels,
			boolean isInitialization)
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
			if (mdef == null) mdef = checkModelDefinitionForAssociation(m, ddr, fakeInitResults);
			if (mdef == null) mdef = buildModelDefinitionForElement(m, ddr, fakeInitResults);
			if (mdef == null) continue;
			if (addedModels.contains(mdef))
			{
				if (inferInitializationModels)
					InitializationModelsInference.saveInitializationModelParams(
							m.getStaticId(),
							mdef,
							ddr,
							PARAMS_NAME);
				continue;
			}
			mdef.setInitialization(isInitialization);
			ddr.addModel(MODELS_NAME, mdef);
			addedModels.add(mdef);

			if (inferInitializationModels)
			{
				Model mdefi = InitializationModelsInference
						.inferInitializationModel(
								m.getStaticId(),
								mdef,
								ddr,
								PARAMS_NAME);
				if (mdefi != null) ddr.addModel(MODELS_NAME, mdefi);
			}
		}
	}

	private Model checkModelDefinitionForType(
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

	private Model checkModelDefinitionForAssociation(
			ModelicaModel m,
			DynamicDataRepositoryDydFiles ddr,
			ModelicaSimulationResults fakeInitializationResults)
	{
		Association a = findAssociation(m);
		if (a == null) return null;

		// The associations relate a single dynamic model with many static network elements
		Model mdef = ddr.getDynamicModelForAssociation(a.getId());
		if (mdef != null)
		{
			// If a template model definition has already been created
			// we only have to save specific parameters for this instance
			saveParametersForModelForAssociation(
					m,
					ddr,
					fakeInitializationResults);
			return mdef;
		}
		ddr.addAssociation(MODELS_NAME, a);
		mdef = buildModelDefinitionForAssociation(
				a.getId(),
				m,
				ddr,
				fakeInitializationResults);

		saveModelForAssociation(m, a);
		return mdef;
	}

	private int							associationCount				= 0;
	private Map<String, Association>	associationsByModelSignature	= new HashMap<>();

	private Association findAssociation(ModelicaModel m)
	{
		String ms = getModelSignature(m);
		Association a = associationsByModelSignature.get(ms);
		if (a == null)
		{
			a = new Association("association" + (associationCount++));
			a.setPattern(m.getStaticId());
		}
		else
		{
			a.setPattern(a.getPattern().concat("|").concat(m.getStaticId()));
		}
		return a;
	}

	private void saveModelForAssociation(ModelicaModel m, Association a)
	{
		String ms = getModelSignature(m);
		associationsByModelSignature.put(ms, a);
	}

	private static String getModelSignature(ModelicaModel m)
	{
		return m.getDeclarations().stream().map(d -> d.getType()).sorted()
				.collect(Collectors.joining(","));
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
		String baseId = "DM{staticId}";
		Model mdef = new ModelForType(stype, baseId);
		boolean isGeneric = true;
		mdef.addConnectors(createConnectors(mo, isGeneric));

		// We only process the first (unique) declaration
		ModelicaDeclaration d = mo.getDeclarations().get(0);

		// A new parameter set for this model
		ParameterSetContainer par = ddr.getParameterSetContainer(PARAMS_NAME);
		ParameterSet pset = par.newParameterSet();
		pset.add(buildParameters(stype, d.getArguments()));
		String componentId = legacyType(stype).concat("_{staticId}");
		Component mdefc = new Component(componentId, d.getType());
		mdefc.setParameterSet(pset);
		mdef.addComponent(mdefc);

		return mdef;
	}

	private static Model buildModelDefinitionForAssociation(
			String associationId,
			ModelicaModel mo,
			DynamicDataRepositoryDydFiles ddr,
			ModelicaSimulationResults fakeInitializationResults)
	{
		String id = mo.getId();
		String staticId = mo.getStaticId();
		if (staticId == null)
		{
			String reason = "staticId is empty and we are trying to generalize a dynamic model so it is valid for an association";
			LOG.warn("Ignored ModelicaModel {}: {}", mo.getId(), reason);
			return null;
		}
		ParameterSetContainer par = ddr.getParameterSetContainer(PARAMS_NAME);
		String type = whichType(mo);

		String baseId = id.replace(staticId, "{staticId}");
		ModelForAssociation mdef = new ModelForAssociation(associationId, baseId);
		boolean isGeneric = true;
		mdef.addConnectors(createConnectors(mo, isGeneric));

		for (ModelicaDeclaration d : mo.getDeclarations())
		{
			String idc = d.getId();
			String idct = idc.replace(staticId, "{staticId}");
			String name = d.getType();
			Component mdefc = new Component(idct, name);
			if (d.getArguments() != null && !d.getArguments().isEmpty())
			{
				// The id of the parameter set is a composition of the static id and the component id
				String did0 = d.getId().replace("_".concat(staticId), "");
				String psetId = staticId.concat("_").concat(did0);
				String psetIdRef = "{staticId}".concat("_").concat(did0);

				ParameterSet pset = new ParameterSet(psetId);
				pset.add(buildParameters(type, d.getArguments()));
				par.add(pset);
				ParameterSetReference pref = new ParameterSetReference(
						par.getName(),
						psetIdRef);
				mdefc.setParameterSetReference(pref);

				// Store values of arguments coming from initialization for later use by fake Modelica engine
				saveInitializationResultsForFakeModelicaEngine(
						staticId,
						idc,
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
				String id1 = idvar1[0].replace(staticId, "{staticId}");
				String var1 = idvar1[1];
				String id2 = idvar2[0].replace(staticId, "{staticId}");
				String var2 = idvar2[1];
				mdef.addConnection(new Connection(id1, var1, id2, var2));
			}
		}
		return mdef;
	}

	private static void saveParametersForModelForAssociation(
			ModelicaModel mo,
			DynamicDataRepositoryDydFiles ddr,
			ModelicaSimulationResults fakeInitializationResults)
	{
		String staticId = mo.getStaticId();
		if (staticId == null)
		{
			String reason = "staticId is empty and we want to store parameters for a specific staticId";
			LOG.warn("Ignored ModelicaModel {}: {}", mo.getId(), reason);
			return;
		}
		ParameterSetContainer par = ddr.getParameterSetContainer(PARAMS_NAME);
		String type = whichType(mo);

		for (ModelicaDeclaration d : mo.getDeclarations())
		{
			if (d.getArguments() != null && !d.getArguments().isEmpty())
			{
				// The id of the parameter set is a composition of the static id and the component id
				String did0 = d.getId().replace("_".concat(staticId), "");
				String psetId = staticId.concat("_").concat(did0);

				ParameterSet pset = new ParameterSet(psetId);
				pset.add(buildParameters(type, d.getArguments()));
				par.add(pset);

				// Store values of arguments coming from initialization for later use by fake Modelica engine
				saveInitializationResultsForFakeModelicaEngine(
						staticId,
						d.getId(),
						d.getArguments(),
						fakeInitializationResults);
			}
		}
	}

	private static Model buildModelDefinitionForElement(
			ModelicaModel mo,
			DynamicDataRepositoryDydFiles ddr,
			ModelicaSimulationResults fakeInitializationResults)
	{
		String id = mo.getId();
		String staticId = mo.getStaticId();
		if (staticId == null)
		{
			String reason = "staticId is empty and we are trying to create a DYD item for a specific element";
			LOG.warn("Ignored ModelicaModel {}: {}", mo.getId(), reason);
			return null;
		}
		ParameterSetContainer par = ddr.getParameterSetContainer(PARAMS_NAME);
		String type = whichType(mo);

		ModelForElement mdef = new ModelForElement(staticId, id);
		boolean isGeneric = false;
		mdef.addConnectors(createConnectors(mo, isGeneric));

		for (ModelicaDeclaration d : mo.getDeclarations())
		{
			String idc = d.getId();
			String name = d.getType();
			Component mdefc = new Component(idc, name);
			if (d.getArguments() != null && !d.getArguments().isEmpty())
			{
				// The id of the parameter set is a composition of the static id and the component id
				String did0 = d.getId().replace("_".concat(staticId), "");
				String psetId = staticId.concat("_").concat(did0);

				ParameterSet pset = new ParameterSet(psetId);
				pset.add(buildParameters(type, d.getArguments()));
				par.add(pset);
				ParameterSetReference pref = new ParameterSetReference(
						par.getName(),
						pset.getId());
				mdefc.setParameterSetReference(pref);

				// Store values of arguments coming from initialization for later use by fake Modelica engine
				saveInitializationResultsForFakeModelicaEngine(
						staticId,
						d.getId(),
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
			String staticId,
			String componentId,
			List<ModelicaArgument> arguments,
			ModelicaSimulationResults fakeInitializationResults)
	{
		for (ModelicaArgument a : arguments)
		{
			Parameter p = checkInitializationReference(a);
			if (p != null)
			{
				fakeInitializationResults.addResult(
						staticId,
						componentId,
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
			String defaultType = null;
			if (p == null) p = new ParameterValue(defaultType, a.getName(), a.getValue());
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

	private static String legacyType(String type)
	{
		switch (type)
		{
		case "Bus":
			return "bus";
		case "Line":
			return "line";
		case "Transformer":
			return "trafo";
		case "Load":
			return "load";
		case "Shunt":
			return "cap";
		default:
			return "";
		}
	}

	private static final String	PARAMS_NAME		= "params.par";
	private static final String	SYSTEM_NAME		= "system";
	private static final String	MODELS_NAME		= "models";
	private static final String	FAKE_INIT_NAME	= "fake_init.csv";

	private static final Logger	LOG				= LoggerFactory
			.getLogger(DydFilesFromModelica.class);
}
