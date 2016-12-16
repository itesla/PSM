package org.power_systems_modelica.psm.ddr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.stream.XMLStreamException;

import org.power_systems_modelica.psm.ddr.InitResults2SimInputs.InitResult;
import org.power_systems_modelica.psm.ddr.InitResults2SimInputs.SimInput;
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
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaTricks;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;
import org.power_systems_modelica.psm.modelica.engine.io.ModelicaSimulationResultsCsv;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DydFilesFromModelica
{
	public void mo2dyd(
			Path modelicaFile,
			Path modelicaInitPath,
			Path ddrLocation)
			throws FileNotFoundException, IOException, XMLStreamException
	{
		// We will try to establish,
		// for every dynamic model declaration and equation seen in the Modelica file,
		// the corresponding element from the static model.
		// We will discard connection equations that refer to distinct static elements (interconnections)
		// because we assume they are built from Network topology and are not required
		// in the dynamic data repository.

		// The DDR we want to build
		DynamicDataRepositoryDydFiles ddr = new DynamicDataRepositoryDydFiles();
		ddr.setLocation(ddrLocation.toString());
		ddr.addParameterSetContainer(PARAMS_NAME);
		ddr.createSystemDefinitions(SYSTEM_NAME);

		// Do we have to infer the initialization models or are they provided explicitly
		boolean inferringInitializationModels = (modelicaInitPath == null);

		// A way to store some values present in the original Modelica files and use them later as if they were initialization results
		ModelicaSimulationFinalResults fakeInitializationResults = new ModelicaSimulationFinalResults();

		// Process all Modelica files
		// Keep track of the associations created
		AssociationsDiscoverer associations = new AssociationsDiscoverer(ddr, MODELS_NAME);
		// Keep track of the Model definitions added to the repository so they are not added twice
		Set<Model> addedModels = new HashSet<>();
		mo2dyd(modelicaFile,
				ddr,
				inferringInitializationModels,
				Stage.SIMULATION,
				associations,
				fakeInitializationResults,
				addedModels);
		if (modelicaInitPath != null)
		{
			// Walk all the ".mo" files found in the folder for initialization files
			Files.walkFileTree(modelicaInitPath, new SimpleFileVisitor<Path>()
			{
				@Override
				public FileVisitResult visitFile(Path modelicaInitFile, BasicFileAttributes attrs)
						throws FileNotFoundException, IOException
				{
					if (modelicaInitFile.toString().endsWith(".mo"))
					{
						mo2dyd(modelicaInitFile,
								ddr,
								inferringInitializationModels,
								Stage.INITIALIZATION,
								associations,
								fakeInitializationResults,
								addedModels);
					}
					return FileVisitResult.CONTINUE;
				}
			});
		}

		// Save the dynamic repository objects as XML files, provide default names for the DYD containers
		ddr.write();

		// Save fake initialization results for later use
		Path fakef = ddrLocation.resolve(FAKE_INIT_NAME);
		ModelicaSimulationResultsCsv.write(fakef, fakeInitializationResults);
	}

	private void mo2dyd(
			Path modelicaFile,
			DynamicDataRepositoryDydFiles ddr,
			boolean inferringInitializationModels,
			Stage stage,
			AssociationsDiscoverer associations,
			ModelicaSimulationFinalResults fakeInitializationResults,
			Set<Model> addedModels)
			throws FileNotFoundException, IOException
	{
		ModelicaDocument mo = ModelicaParser.parse(modelicaFile);
		Collection<ModelicaModel> ms = ModelicaUtil
				.groupByNormalizedStaticId(mo)
				.values();
		ms = ms.stream()
				.filter(m -> !ModelicaUtil.isInterconnection(m))
				.collect(Collectors.toList());
		models2dyd(ms,
				ddr,
				inferringInitializationModels,
				stage,
				associations,
				fakeInitializationResults,
				addedModels);
	}

	private void models2dyd(
			Collection<ModelicaModel> ms,
			DynamicDataRepositoryDydFiles ddr,
			boolean inferringInitializationModels,
			Stage stage,
			AssociationsDiscoverer associations,
			ModelicaSimulationFinalResults fakeInitResults,
			Set<Model> addedModels)
	{
		for (ModelicaModel m : ms)
		{
			if (ModelicaUtil.isSystemModel(m))
			{
				ddr.addSystemDeclarations(m.getDeclarations(), stage);
				ddr.addSystemEquations(m.getEquations(), stage);
				continue;
			}

			Model mdef = checkModel(m, ddr, stage, associations, fakeInitResults);
			if (mdef == null) continue;

			if (addedModels.contains(mdef))
			{
				if (inferringInitializationModels)
					InitializationModelsInference.saveInitializationModelParams(
							m.getStaticId(),
							mdef,
							ddr,
							PARAMS_NAME);
				continue;
			}

			ddr.addModel(MODELS_NAME, mdef);
			addedModels.add(mdef);

			if (inferringInitializationModels)
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

	private Model checkModel(
			ModelicaModel m,
			DynamicDataRepositoryDydFiles ddr,
			Stage stage,
			AssociationsDiscoverer associations,
			ModelicaSimulationFinalResults fakeInitResults)
	{
		Model mdef = checkModelDefinitionForType(m, ddr, stage);
		if (mdef == null)
			mdef = checkModelDefinitionForAssociation(m, ddr, stage, associations, fakeInitResults);
		if (mdef == null) mdef = buildModelDefinitionForElement(m, ddr, stage, fakeInitResults);
		return mdef;
	}

	private Model checkModelDefinitionForType(
			ModelicaModel m,
			DynamicDataRepositoryDydFiles ddr,
			Stage stage)
	{
		String type = whichType(m);
		if (type == null) return null;

		Model mdef = ddr.getDynamicModelForStaticType(type, stage);
		if (mdef != null) return mdef;

		// Do not build generic model definition for generators
		if (type.equals("Generator")) return null;

		return buildModelDefinitionForType(type, m, stage, ddr);
	}

	private Model checkModelDefinitionForAssociation(
			ModelicaModel m,
			DynamicDataRepositoryDydFiles ddr,
			Stage stage,
			AssociationsDiscoverer associations,
			ModelicaSimulationFinalResults fakeInitializationResults)
	{
		Association a = associations.findCreateAssociation(m, stage);
		if (a == null) return null;

		// The associations relate a single dynamic model with many static network elements
		Model mdef = ddr.getDynamicModelForAssociation(a.getId(), stage);
		if (mdef != null)
		{
			// If a template model definition has already been created
			// we only have to save specific parameters for this instance
			saveParametersForModel(
					mdef,
					m,
					ddr,
					fakeInitializationResults);
			return mdef;
		}
		mdef = buildModelDefinitionForAssociation(
				a.getId(),
				m,
				stage,
				ddr,
				fakeInitializationResults);
		mdef.setStage(stage);

		return mdef;
	}

	private static String whichType(ModelicaModel mo)
	{
		ModelicaDeclaration d = mo.getDeclarations().get(0);
		String dtype = d.getType();
		String stype = ModelicaTricks.getStaticTypeFromDynamicType(dtype);
		if (stype == null)
		{
			String message = "Can not identify static type from dynamic type '" + dtype + "'";
			LOG.error(message);
			throw new RuntimeException(message);
		}
		return stype;
	}

	private static Model buildModelDefinitionForType(
			String stype,
			ModelicaModel mo,
			Stage stage,
			DynamicDataRepositoryDydFiles ddr)
	{
		String baseId = "DM{staticId}";
		Model mdef = new ModelForType(stype, baseId);
		mdef.setStage(stage);
		boolean isGeneric = true;
		mdef.addConnectors(createConnectors(mo, isGeneric));

		// We only process the first (unique) declaration
		ModelicaDeclaration d = mo.getDeclarations().get(0);

		// A new parameter set for this model
		ParameterSetContainer par = ddr.getParameterSetContainer(PARAMS_NAME);
		ParameterSet pset = par.newParameterSet();
		String staticId = null;
		pset.add(buildParameters(staticId, stype, d.getId(), d.getArguments()));
		String componentId = legacyType(stype).concat("_{staticId}");
		Component mdefc = new Component(componentId, d.getType());
		mdefc.setParameterSet(pset);
		mdef.addComponent(mdefc);

		return mdef;
	}

	private static Model buildModelDefinitionForAssociation(
			String associationId,
			ModelicaModel mo,
			Stage stage,
			DynamicDataRepositoryDydFiles ddr,
			ModelicaSimulationFinalResults fakeInitializationResults)
	{
		String id = mo.getId();
		String staticId = mo.getStaticId();
		if (staticId == null)
		{
			String reason = "staticId is empty and we are trying to generalize a dynamic model so it is valid for an association";
			LOG.warn("Ignored ModelicaModel {}: {}", mo.getId(), reason);
			return null;
		}

		String baseId = id.replace(staticId, "{staticId}");
		ModelForAssociation mdef = new ModelForAssociation(associationId, baseId);
		mdef.setStage(stage);
		boolean isGeneric = true;
		mdef.addConnectors(createConnectors(mo, isGeneric));

		for (ModelicaDeclaration d : mo.getDeclarations())
		{
			String idc = d.getId();
			String idct = idc.replace(staticId, "{staticId}");
			String type = d.getType();
			Component mdefc = new Component(idct, type);
			if (d.getArguments() != null && !d.getArguments().isEmpty())
			{
				// The id of the parameter set is a composition of the static id and the component id
				String did0 = d.getId().replace("_".concat(staticId), "");
				String psetIdRef = "{staticId}".concat("_").concat(did0).concat("_")
						.concat(mdef.getStage().name());
				ParameterSetReference pref = new ParameterSetReference(
						PARAMS_NAME,
						psetIdRef);
				mdefc.setParameterSetReference(pref);
			}
			mdef.addComponent(mdefc);
		}
		saveParametersForModel(mdef, mo, ddr, fakeInitializationResults);

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

	private static void saveParametersForModel(
			Model mdef,
			ModelicaModel mo,
			DynamicDataRepositoryDydFiles ddr,
			ModelicaSimulationFinalResults fakeInitializationResults)
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
				String psetId = staticId.concat("_").concat(did0).concat("_")
						.concat(mdef.getStage().name());

				ParameterSet pset = new ParameterSet(psetId);
				pset.add(buildParameters(staticId, type, d.getId(), d.getArguments()));
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
			Stage stage,
			ModelicaSimulationFinalResults fakeInitializationResults)
	{
		String id = mo.getId();
		String staticId = mo.getStaticId();
		if (staticId == null)
		{
			String reason = "staticId is empty and we are trying to create a DYD item for a specific element";
			LOG.warn("Ignored ModelicaModel {}: {}", mo.getId(), reason);
			return null;
		}

		ModelForElement mdef = new ModelForElement(staticId, id);
		mdef.setStage(stage);
		boolean isGeneric = false;
		mdef.addConnectors(createConnectors(mo, isGeneric));

		for (ModelicaDeclaration d : mo.getDeclarations())
		{
			String idc = d.getId();
			String type = d.getType();
			Component mdefc = new Component(idc, type);
			if (d.getArguments() != null && !d.getArguments().isEmpty())
			{
				// The id of the parameter set is a composition of the static id and the component id
				String did0 = d.getId().replace("_".concat(staticId), "");
				String psetId = staticId.concat("_").concat(did0).concat("_")
						.concat(mdef.getStage().name());
				;
				ParameterSetReference pref = new ParameterSetReference(PARAMS_NAME, psetId);
				mdefc.setParameterSetReference(pref);
			}
			mdef.addComponent(mdefc);
		}
		saveParametersForModel(mdef, mo, ddr, fakeInitializationResults);

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
			ModelicaSimulationFinalResults fakeInitializationResults)
	{
		for (ModelicaArgument a : arguments)
		{
			Parameter p = checkInitializationReference(staticId, componentId, a);
			if (p != null)
			{
				String var = ((ParameterReference) p).getSourceName();
				fakeInitializationResults.addResult(
						staticId,
						var,
						a.getValue());
			}
		}
	}

	private static List<Parameter> buildParameters(
			String staticId,
			String stype,
			String componentId,
			List<ModelicaArgument> arguments)
	{
		List<Parameter> params = new ArrayList<>(arguments.size());
		for (ModelicaArgument a : arguments)
		{
			Parameter p = null;

			p = checkInitializationReference(staticId, componentId, a);
			if (p == null) p = checkIidmReference(stype, a);
			String defaultType = null;
			String defaultUnit = null;
			if (p == null)
				p = new ParameterValue(defaultType, defaultUnit, a.getName(), a.getValue());
			params.add(p);
		}
		return params;
	}

	private static Parameter checkInitializationReference(
			String staticId,
			String componentId,
			ModelicaArgument a)
	{
		// Search for a generic mapping that does not contain the "_<staticId>" in the componentId
		String var = a.getName();
		String componentId1 = componentId;
		if (staticId != null) componentId1 = componentId1.replace("_".concat(staticId), "");
		SimInput simInput = new SimInput(componentId1, var);
		InitResult initResult = initResults2SimInputs.getInitResultFromSimInput(simInput);
		if (initResult != null)
		{
			// Now build a reference to an initialization component that contains the staticId
			String initResultsReference = String.format("%s_%s.%s",
					initResult.component,
					staticId,
					initResult.var);

			String defaultUnit = null;
			return new ParameterReference(a.getName(), defaultUnit, "INIT", initResultsReference);
		}
		return null;
	}

	private static Parameter checkIidmReference(String stype, ModelicaArgument a)
	{
		String iidmAttribute = IidmNames.getIidmNameForModelicaArgument(stype, a.getName());
		if (iidmAttribute != null)
		{
			String defaultUnit = null;
			return new ParameterReference(a.getName(), defaultUnit, "IIDM", iidmAttribute);
		}
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

	private static final String					PARAMS_NAME		= "params.par";
	private static final String					SYSTEM_NAME		= "system";
	private static final String					MODELS_NAME		= "models";
	private static final String					FAKE_INIT_NAME	= "fake_init.csv";

	private static final InitResults2SimInputs	initResults2SimInputs;
	static
	{
		Path refsCsv = Paths.get(System.getenv("PSM_DATA"))
				.resolve("test")
				.resolve("dyd_files_from_modelica")
				.resolve("init_results_to_sim_input")
				.resolve("refs.csv");
		initResults2SimInputs = new InitResults2SimInputs(refsCsv);
	}

	private static final Logger LOG = LoggerFactory
			.getLogger(DydFilesFromModelica.class);
}
