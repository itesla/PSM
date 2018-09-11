package org.power_systems_modelica.psm.mo2dyd;

/*
 * #%L
 * DYD files from Modelica
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.stream.XMLStreamException;

import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.dd.Association;
import org.power_systems_modelica.psm.dd.Component;
import org.power_systems_modelica.psm.dd.Connection;
import org.power_systems_modelica.psm.dd.Interconnection;
import org.power_systems_modelica.psm.dd.Model;
import org.power_systems_modelica.psm.dd.ModelForAssociation;
import org.power_systems_modelica.psm.dd.ModelForElement;
import org.power_systems_modelica.psm.dd.ModelForType;
import org.power_systems_modelica.psm.dd.Parameter;
import org.power_systems_modelica.psm.dd.ParameterReference;
import org.power_systems_modelica.psm.dd.ParameterSet;
import org.power_systems_modelica.psm.dd.ParameterSetReference;
import org.power_systems_modelica.psm.dd.ParameterValue;
import org.power_systems_modelica.psm.dd.Stage;
import org.power_systems_modelica.psm.dd.StaticType;
import org.power_systems_modelica.psm.dd.equations.Equation;
import org.power_systems_modelica.psm.dd.equations.UnparsedEquation;
import org.power_systems_modelica.psm.ddr.dyd.DynamicDataRepositoryDydFiles;
import org.power_systems_modelica.psm.ddr.dyd.ParameterSetContainer;
import org.power_systems_modelica.psm.mo2dyd.InitResults2SimInputs.InitResult;
import org.power_systems_modelica.psm.mo2dyd.InitResults2SimInputs.SimInput;
import org.power_systems_modelica.psm.modelica.BaseModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
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

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class DydFilesFromModelica
{
	public DydFilesFromModelica(
			Path modelicaFile,
			Path modelicaInitPath,
			Path ddrLocation)
			throws FileNotFoundException, IOException, XMLStreamException
	{	
		this.modelicaFile = modelicaFile;
		this.modelicaInitPath = modelicaInitPath;
		this.ddrLocation = ddrLocation;

		// We will try to establish,
		// for every dynamic model declaration and equation seen in the Modelica file,
		// the corresponding element from the static model.
		// We will discard connection equations that refer to distinct static elements (interconnections)
		// because we assume they are built from Network topology and are not required
		// in the dynamic data repository.

		// The DDR we want to build
		ddr = new DynamicDataRepositoryDydFiles();
		ddr.setLocation(ddrLocation.toString());
		ddr.addParameterSetContainer(PARAMS_NAME);

		// Do we have to infer the initialization models or are they provided explicitly
		inferringInitializationModels = (modelicaInitPath == null);

		// A way to store some values present in the original Modelica files and use them later as if they were initialization results
		fakeInitializationResults = new ModelicaSimulationFinalResults();

		// Process all Modelica files
		// Keep track of the associations created
		associations = new AssociationsDiscoverer(ddr, MODELS_NAME);
		// Keep track of the Model definitions added to the repository so they are not added twice
		addedModels = new HashSet<>();
	}

	public void mo2dyd() throws IOException, XMLStreamException
	{
		processMoFiles();
		ddr.write();

		// Save fake initialization results for later use
		Path fakef = ddrLocation.resolve(FAKE_INIT_NAME);
		ModelicaSimulationResultsCsv.write(fakef, fakeInitializationResults);
	}

	private void processMoFiles() throws IOException, XMLStreamException
	{
		processMoFile(modelicaFile, Stage.SIMULATION);
		hardcodedModels2dyd(Stage.SIMULATION);
		if (modelicaInitPath == null) return;

		// Walk all the Modelica files found in the folder for initialization files
		Files.walkFileTree(modelicaInitPath, new SimpleFileVisitor<Path>()
		{
			@Override
			public FileVisitResult visitFile(Path modelicaInitFile, BasicFileAttributes attrs)
					throws FileNotFoundException, IOException
			{
				if (modelicaInitFile.toString().endsWith(".mo"))
					processMoFile(modelicaInitFile, Stage.INITIALIZATION);
				return FileVisitResult.CONTINUE;
			}
		});
	}

	private void processMoFile(Path modelicaFile, Stage stage)
			throws FileNotFoundException, IOException
	{
		ModelicaDocument mo = ModelicaParser.parse(modelicaFile);
		Collection<ModelicaModel> ms = ModelicaUtil
				.groupByNormalizedStaticId(mo)
				.values();
		ms = ms.stream()
				.filter(m -> !ModelicaUtil.isInterconnection(m))
				.collect(Collectors.toList());
		checkOmegaRef(ms);
		models2dyd(ms, stage);
	}

	private void checkOmegaRef(Collection<ModelicaModel> models)
	{
		omegaRefModel = models.stream()
				.filter(ModelicaUtil::isSystemModel)
				.findFirst()
				.map(m -> {
					return m.getDeclarations().stream()
							.filter(d -> d.getId().equals("omegaRef"))
							.findFirst()
							.map(d0 -> {
								if (d0.getType().endsWith(".RealOutput"))
									return OmegaRefModel.REAL_OUTPUT;
								else if (d0.getType().endsWith(".Constant"))
									return OmegaRefModel.CONSTANT_VALUE;
								else if (d0.getType().endsWith(".omegaRef"))
									return OmegaRefModel.COMPONENT;
								else
									return OmegaRefModel.UNKNOWN;
							})
							.orElse(OmegaRefModel.NO_MODEL);
				})
				.orElse(OmegaRefModel.NO_MODEL);
	}

	private void models2dyd(Collection<ModelicaModel> ms, Stage stage)
	{
		for (ModelicaModel mo : ms)
		{
			if (ModelicaUtil.isSystemModel(mo))
			{
				processSystemModel(mo, stage);
				continue;
			}
			Model mdef = findOrBuildModelDefinition(mo, stage);
			if (mdef == null)
				continue;
			// If the model has been already added (as a generic definition)
			// We only have to process the parameters of this instance
			// to be used for potential initialization models
			if (addedModels.contains(mdef))
			{
				processParamsForInitialization(mo, mdef);
				continue;
			}
			ddr.addModel(MODELS_NAME, mdef);
			addedModels.add(mdef);
			processModelForInitialization(mo, mdef);
		}
	}

	private void hardcodedModels2dyd(Stage stage)
	{
		String baseId = "DM{staticId}";
		Model mdef = new ModelForType(StaticType.Switch, baseId);
		Interconnection i = Interconnection.createDoubleTarget("{bus1}", "p", "{bus2}", "p");
		mdef.addInterconnections(Arrays.asList(i));
		ddr.addModel(MODELS_NAME, mdef);
	}

	private void processSystemModel(ModelicaModel m, Stage stage)
	{
		Model mdef = ddr.getDynamicModelForSystem(stage);
		if (mdef == null)
		{
			mdef = buildModelDefinitionForSystem(m, stage);
			ddr.addModel(SYSTEM_NAME, mdef);
		}
		else
		{
			completeModelDefinitionForSystem(mdef, m, stage);
		}
	}

	private void processParamsForInitialization(ModelicaModel m, Model mdef)
	{
		if (inferringInitializationModels)
			InitializationModelsInference.saveInitModelParams(
					m.getStaticId(),
					mdef,
					ddr,
					PARAMS_NAME);
	}

	private void processModelForInitialization(ModelicaModel m, Model mdef)
	{
		if (inferringInitializationModels)
		{
			Model mdefi = InitializationModelsInference.inferModel(
					m.getStaticId(),
					mdef,
					ddr,
					PARAMS_NAME);
			if (mdefi != null) ddr.addModel(MODELS_NAME, mdefi);
		}
	}

	private Model findOrBuildModelDefinition(ModelicaModel m, Stage stage)
	{
		Model mdef = findOrBuildModelDefinitionForType(m, stage);
		if (mdef == null) mdef = findOrBuildModelDefinitionForAssociation(m, stage);
		if (mdef == null) mdef = buildModelDefinitionForElement(m, stage);
		return mdef;
	}

	private Model findOrBuildModelDefinitionForType(ModelicaModel m, Stage stage)
	{
		// Infinite buses will not receive a model based on their type,
		// A model definition will be created for every infinite bus identifier
		if (ModelicaTricks.isInfiniteBus(m)) return null;

		StaticType staticType = StaticType.from(m);
		if (staticType == null) return null;

		Model mdef = ddr.getDynamicModelForStaticType(staticType, stage);
		if (mdef != null)
		{
			boolean isGenericModel = true;
			saveParametersForModel(mdef, m, isGenericModel);
			return mdef;
		}

		// FIXME Default modeling for generators should be a fixed injection (or configurable)
		// Currently we do not build generic model definition for static type Generator
		if (staticType.equals(StaticType.Generator)) return null;

		return buildModelDefinitionForStaticType(staticType, m, stage);
	}

	private Model findOrBuildModelDefinitionForAssociation(ModelicaModel m, Stage stage)
	{
		// Infinite buses will not build an association,
		// A model definition will be created for every infinite bus identifier
		if (ModelicaTricks.isInfiniteBus(m)) return null;

		Association a = associations.findCreateAssociation(m, stage);
		if (a == null) return null;

		// The associations relate a single dynamic model with many static network elements
		Model mdef = ddr.getDynamicModelForAssociation(a.getId(), stage);
		if (mdef != null)
		{
			// If a template model definition has already been created
			// we only have to save specific parameters for this instance
			boolean isGenericModel = true;
			saveParametersForModel(mdef, m, isGenericModel);
			return mdef;
		}
		mdef = buildModelDefinitionForAssociation(a.getId(), m, stage);
		mdef.setStage(stage);

		return mdef;
	}

	private Model buildModelDefinitionForStaticType(StaticType stype, ModelicaModel mo, Stage stage)
	{
		boolean isGenericModel = true;
		String baseId = "DM{staticId}";
		Model mdef = new ModelForType(stype, baseId);
		modelicaModel2ModelDefinition(mo, mdef, stage, isGenericModel);
		return mdef;
	}

	private Model buildModelDefinitionForAssociation(
			String associationId,
			ModelicaModel mo,
			Stage stage)
	{
		String staticId = mo.getStaticId();
		if (staticId == null)
		{
			String reason = "staticId is empty and we are trying to isGenericModel a dynamic model so it is valid for an association";
			LOG.warn("Ignored ModelicaModel {}: {}", mo.getId(), reason);
			return null;
		}
		boolean isGenericModel = true;
		String baseId = mo.getId().replace(staticId, "{staticId}");
		ModelForAssociation mdef = new ModelForAssociation(associationId, baseId);
		modelicaModel2ModelDefinition(mo, mdef, stage, isGenericModel);
		return mdef;
	}

	private Model buildModelDefinitionForElement(ModelicaModel mo, Stage stage)
	{
		String staticId = mo.getStaticId();
		if (staticId == null)
		{
			String reason = "staticId is empty and we are trying to create a dynamic model for a specific element";
			LOG.warn("Ignored ModelicaModel {}: {}", mo.getId(), reason);
			return null;
		}
		boolean isGenericModel = false;
		ModelForElement mdef = new ModelForElement(staticId, mo.getId());
		modelicaModel2ModelDefinition(mo, mdef, stage, isGenericModel);
		return mdef;
	}

	private Model buildModelDefinitionForSystem(ModelicaModel mo, Stage stage)
	{
		String staticId = mo.getStaticId();
		if (staticId == null || !staticId.equals(ModelicaUtil.getSystemStaticId()))
		{
			String reason = String.format(
					"staticId = '{}' is empty or wrong for system definitions, should be equal to '{}'",
					mo.getStaticId(),
					ModelicaUtil.getSystemStaticId());
			LOG.warn("Ignored ModelicaModel {}: {}",
					mo.getId(),
					mo.getStaticId(),
					reason);
			return null;
		}
		boolean isGenericModel = false;
		ModelForElement mdef = new ModelForElement(staticId, mo.getId());
		modelicaModel2ModelDefinition(mo, mdef, stage, isGenericModel);
		return mdef;
	}

	private void completeModelDefinitionForSystem(Model mdef, ModelicaModel mo, Stage stage)
	{
		String staticId = mo.getStaticId();
		if (staticId == null || !staticId.equals(ModelicaUtil.getSystemStaticId()))
		{
			String reason = String.format(
					"staticId = '{}' is empty or wrong for system definitions, should be equal to '{}'",
					mo.getStaticId(),
					ModelicaUtil.getSystemStaticId());
			LOG.warn("Ignored ModelicaModel {}: {}",
					mo.getId(),
					mo.getStaticId(),
					reason);
			return;
		}
		boolean isGenericModel = true;
		modelicaModel2ModelDefinition(mo, mdef, stage, isGenericModel);
	}

	private void modelicaModel2ModelDefinition(ModelicaModel mo, Model mdef, Stage stage,
			boolean isGenericModel)
	{
		mdef.setStage(stage);
		mdef.addInterconnections(createInterconnections(mo, stage, isGenericModel));
		moDeclarations2modelComponents(mo, mdef, isGenericModel);
		moEquations2modelEquations(mo, mdef, isGenericModel);
	}

	private void moDeclarations2modelComponents(
			ModelicaModel mo,
			Model mdef,
			boolean isGenericModel)
	{
		StaticType staticType = StaticType.from(mo);
		String staticId = mo.getStaticId();
		for (BaseModelicaDeclaration d : mo.getDeclarations())
		{
			String idc = d.getId();
			if (isGenericModel) idc = idc.replace(staticId, "{staticId}");
			String type = d.getType();
			Component c = new Component(idc, type);
			if (d.isAssignment())
			{
				c.setValue(d.getValue());
				c.setParameter(d.isParameter());
			}
			if (d.getArguments() != null && !d.getArguments().isEmpty())
			{
				List<Parameter> params = buildParameters(staticId, staticType, d.getId(),
						d.getArguments());
				if (parametersShouldBeExternal(params, d, isGenericModel))
				{
					String psetRefId = idc.concat("_").concat(mdef.getStage().name());
					ParameterSetReference psetRef;
					psetRef = new ParameterSetReference(PARAMS_NAME, psetRefId);
					c.setParameterSetReference(psetRef);
				}
				else
				{
					ParameterSetContainer par = ddr.getParameterSetContainer(PARAMS_NAME);
					ParameterSet pset = par.newParameterSet();
					pset.add(params);
					c.setParameterSet(pset);
				}
			}
			mdef.addComponent(c);
		}
		saveParametersForModel(mdef, mo, isGenericModel);
	}

	private void moEquations2modelEquations(ModelicaModel mo, Model mdef, boolean isGenericModel)
	{
		String staticId = mo.getStaticId();
		for (ModelicaEquation eq : mo.getEquations())
		{
			if (eq instanceof ModelicaConnect)
			{
				ModelicaConnect eqc = (ModelicaConnect) eq;
				String[] idvar1 = ModelicaUtil.ref2idvar(eqc.getRef1());
				String[] idvar2 = ModelicaUtil.ref2idvar(eqc.getRef2());
				String id1 = idvar1[0];
				if (isGenericModel) id1 = id1.replace(staticId, "{staticId}");
				String var1 = idvar1[1];
				String id2 = idvar2[0];
				if (isGenericModel) id2 = id2.replace(staticId, "{staticId}");
				String var2 = idvar2[1];
				mdef.addConnection(new Connection(id1, var1, id2, var2));
			}
			else
			{
				Equation eq1 = new UnparsedEquation(eq.getText());
				mdef.addOtherEquation(eq1);
			}
		}
	}

	private boolean parametersShouldBeExternal(
			List<Parameter> params,
			BaseModelicaDeclaration d,
			boolean isGenericModel)
	{
		// If we are building a model that is generic or storing parameters for an instance of a generic model
		// all parameters should be stored externally unless all of them are generic:
		// if all are generic we do not see different values per instance and we can in-line them
		if (isGenericModel)
		{
			boolean allParamsGeneric = params.stream().allMatch(p -> p.isGeneric());
			return !allParamsGeneric;
		}
		// Short lists of parameters could go in-line inside the corresponding component of the model definition
		if (d.getArguments().size() > 5) return true;
		return false;
	}

	private void saveParametersForModel(Model mdef, ModelicaModel mo, boolean isGenericModel)
	{
		String staticId = mo.getStaticId();
		if (staticId == null)
		{
			String reason = "staticId is empty and we want to store parameters for a specific staticId";
			LOG.warn("Ignored ModelicaModel {}: {}", mo.getId(), reason);
			return;
		}
		ParameterSetContainer par = ddr.getParameterSetContainer(PARAMS_NAME);
		StaticType staticType = StaticType.from(mo);
		for (BaseModelicaDeclaration d : mo.getDeclarations())
		{
			if (d.getArguments() != null && !d.getArguments().isEmpty())
			{
				Component c = getComponentForDeclaration(d, mo, mdef, isGenericModel);
				if (c.getParameterSetReference() != null)
				{
					String psetId = c.getParameterSetReference().getSet();
					if (isGenericModel) psetId = psetId.replace("{staticId}", staticId);
					ParameterSet pset = new ParameterSet(psetId);
					pset.add(buildParameters(staticId, staticType, d.getId(), d.getArguments()));
					par.add(pset);
				}
				// Also store values of arguments coming from initialization for later use by fake Modelica engine
				saveInitializationResultsForFakeModelicaEngine(
						staticId,
						d.getId(),
						d.getArguments(),
						fakeInitializationResults);
			}
		}
	}

	private static Component getComponentForDeclaration(
			BaseModelicaDeclaration d,
			ModelicaModel mo,
			Model mdef,
			boolean isGenericModel)
	{
		String staticId = mo.getStaticId();
		String idc = d.getId();
		if (isGenericModel) idc = idc.replace(staticId, "{staticId}");
		String idcf = idc;
		Component c = mdef.getComponents().stream()
				.filter(c0 -> c0.getId().equals(idcf))
				.findFirst()
				.orElse(null);
		if (c == null)
		{
			String msg = String.format("Component not found id = '%s', for declaration = '%s'",
					idc,
					d.getId());
			LOG.error(msg);
			throw new RuntimeException(msg);
		}
		return c;
	}

	private static void saveInitializationResultsForFakeModelicaEngine(
			String staticId,
			String componentId,
			List<ModelicaArgument> arguments,
			ModelicaSimulationFinalResults fakeInitializationResults)
	{
		for (ModelicaArgument a : arguments)
		{
			Parameter p = asInitializationReference(staticId, componentId, a);
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
			StaticType stype,
			String declarationId,
			List<ModelicaArgument> arguments)
	{
		List<Parameter> params = new ArrayList<>(arguments.size());
		for (ModelicaArgument a : arguments)
		{
			Parameter p = null;

			p = asInitializationReference(staticId, declarationId, a);
			if (p == null) p = asIidmReference(stype, a);
			if (p == null) p = asDynamicNetworkReference(staticId, declarationId, a);
			if (p == null) p = asGenericParameterValue(stype, declarationId, a);
			if (p == null)
			{
				String defaultType = null;
				String defaultUnit = null;
				p = new ParameterValue(defaultType, defaultUnit, a.getName(), a.getValue());
			}
			params.add(p);
		}
		return params;
	}

	private static Parameter asGenericParameterValue(
			StaticType staticType,
			String declarationId,
			ModelicaArgument a)
	{
		boolean isGeneric = false;

		String kTypeArgname = String.format("%s::%s", staticType.name(), a.getName());
		if (GENERIC_PARAMETERS_TYPE.contains(kTypeArgname)) isGeneric = true;
		if (declarationId.startsWith("zero_")) isGeneric = true;

		if (isGeneric)
		{
			String paramType = null;
			String paramUnit = null;
			ParameterValue p = new ParameterValue(
					paramType, paramUnit,
					a.getName(),
					a.getValue());
			p.setGeneric(true);
			return p;
		}
		return null;
	}

	private static Parameter asInitializationReference(
			String staticId,
			String declarationId,
			ModelicaArgument a)
	{
		// Search for a generic mapping that does not contain the "_<staticId>" in the componentId
		String var = a.getName();
		String componentId1 = declarationId;
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

	private static Parameter asIidmReference(StaticType stype, ModelicaArgument a)
	{
		String iidmAttribute = getIidmNameForModelicaArgument(stype, a.getName());
		if (iidmAttribute != null)
		{
			String defaultUnit = null;
			return new ParameterReference(a.getName(), defaultUnit, "IIDM", iidmAttribute);
		}
		return null;
	}

	private static Parameter asDynamicNetworkReference(String staticId, String declarationId,
			ModelicaArgument a)
	{
		if (staticId != null && staticId.equals(ModelicaUtil.getSystemStaticId()))
		{
			String dynnParameterKey = String.format("%s::%s", declarationId, a.getName());
			if (DYNN_PARAMETERS.containsKey(dynnParameterKey))
			{
				String defaultUnit = null;
				String dynnName = DYNN_PARAMETERS.get(dynnParameterKey);
				return new ParameterReference(a.getName(), defaultUnit, "DYNN", dynnName);
			}
		}
		return null;
	}

	private List<Interconnection> createInterconnections(
			ModelicaModel m,
			Stage stage,
			boolean isGeneric)
	{
		if (m.getDeclarations().isEmpty()) return Collections.emptyList();
		if (stage.equals(Stage.INITIALIZATION)) return Collections.emptyList();

		String firstDeclarationId = m.getDeclarations().get(0).getId();
		String firstDeclarationType = m.getDeclarations().get(0).getType();
		boolean isBranch = firstDeclarationId.startsWith("line_")
				|| firstDeclarationId.startsWith("trafo_")
				|| firstDeclarationId.startsWith("Line_")
				|| firstDeclarationId.startsWith("Transformer");
		boolean isGenerator = firstDeclarationId.startsWith("gen_");
		boolean isLoadOmegaRef = firstDeclarationId.startsWith("load_")
				&& firstDeclarationType.contains("FreqDependence");
		boolean isBus = firstDeclarationId.startsWith("bus_")
				|| firstDeclarationId.startsWith("Bus_");
		boolean isSystem = ModelicaUtil.isSystemModel(m);
		String componentId = firstDeclarationId;
		if (isGeneric) componentId = firstDeclarationId.replace(m.getStaticId(), "{staticId}");

		// Buses have one interconnection (a receiver: name and link with internal component and var)
		// Branches have two interconnections (one sender to corresponding bus at each end)
		// Generators have multiple connections related to omegaRef and one sender to the bus
		// For the rest of equipment types we will create one sender to the bus
		if (isSystem) return createInterconnectionsOmegaRef();
		else if (isBus) return Arrays.asList(
				Interconnection.createReceiver("p", componentId, "p"));
		else if (isBranch) return Arrays.asList(
				Interconnection.createSender(componentId, "p", "{bus1}", "p"),
				Interconnection.createSender(componentId, "n", "{bus2}", "p"));
		else if (isGenerator) return createInterconnectionsGenerator(componentId);
		else if (isLoadOmegaRef) return Arrays.asList(
				Interconnection.createSender(componentId, "p", "{bus}", "p"),
				Interconnection.createSender(componentId, "omegaRef", "{system}", "omegaRef"));
		else return Arrays.asList(
				Interconnection.createSender(componentId, "p", "{bus}", "p"));
	}

	private List<Interconnection> createInterconnectionsOmegaRef()
	{
		switch (omegaRefModel)
		{
		case COMPONENT:
			return Arrays.asList(
					Interconnection.createReceiver("omegaRef", "omegaRef", "omegaRef"),
					Interconnection.createReceiver("omegaRef_HIn[]", "omegaRef", "pin_HIn[]"),
					Interconnection.createReceiver("omegaRef_SN[]", "omegaRef", "pin_SN[]"),
					Interconnection.createReceiver("omegaRef_omega[]", "omegaRef", "pin_omega[]"));
		case REAL_OUTPUT:
			return Arrays.asList(Interconnection.createReceiver("omegaRef", null, "omegaRef"));
		case CONSTANT_VALUE:
			return Arrays.asList(Interconnection.createReceiver("omegaRef", "omegaRef", "y"));
		default:
			return Collections.emptyList();
		}
	}

	private List<Interconnection> createInterconnectionsGenerator(String cid)
	{
		switch (omegaRefModel)
		{
		case COMPONENT:
			return Arrays.asList(
					Interconnection.createSender(cid, "sortie", "{bus}", "p"),
					Interconnection.createSender(cid, "omegaRef", "{system}", "omegaRef"),
					Interconnection.createSender(cid, "pin_HIn",
							"{system}", "omegaRef_HIn[?]"),
					Interconnection.createSender(cid, "pin_SN",
							"{system}", "omegaRef_SN[?]"),
					Interconnection.createSender(cid, "pin_OMEGA",
							"{system}", "omegaRef_omega[?]"));
		case REAL_OUTPUT:
			return Arrays.asList(
					Interconnection.createSender(cid, "sortie", "{bus}", "p"),
					Interconnection.createSender(cid, "omegaRef", "{system}", "omegaRef"));
		case CONSTANT_VALUE:
			return Arrays.asList(
					Interconnection.createSender(cid, "sortie", "{bus}", "p"),
					Interconnection.createSender(cid, "omegaRef", "{system}", "omegaRef"));
		default:
			return Arrays.asList(
					Interconnection.createSender(cid, "sortie", "{bus}", "p"));
		}
	}
	
	private static String getIidmNameForModelicaArgument(StaticType stype, String mname)
	{		
		if(properties != null) {
			return properties.getProperty(String.format("%s.%s", stype, mname));
		}
		else return null;
	}

	private static final String					PARAMS_NAME				= "params.par";
	private static final String					SYSTEM_NAME				= "system";
	private static final String					MODELS_NAME				= "models";
	private static final String					FAKE_INIT_NAME			= "fake_init.csv";

	private static final Set<String>			GENERIC_PARAMETERS_TYPE	= new HashSet<>(
			// We consider parameters alpha and beta of models for type Load to be constant for all instances
			Arrays.asList(
					"Load::alpha",
					"Load::beta"));

	private static final Map<String, String>	DYNN_PARAMETERS			= new HashMap<>();
	static
	{
		// We create a DYNN resolved parameter for the nGenerators variable of the omegaRef declaration
		DYNN_PARAMETERS.put("omegaRef::nGenerators", "numModelsConnectToTarget({system},omegaRef)");
	}

	private static final Logger					LOG	= LoggerFactory
			.getLogger(DydFilesFromModelica.class);

	private static final InitResults2SimInputs	initResults2SimInputs;
	static
	{
		Path refs = PathUtils.DATA_TEST
				.resolve("dyd_files_from_modelica")
				.resolve("init_results_to_sim_input")
				.resolve("refs.csv");
		initResults2SimInputs = new InitResults2SimInputs();
		try
		{
			initResults2SimInputs.read(refs);
		}
		catch (IOException e)
		{
			LOG.error("Processing init results 2 sim inputs references");
			e.printStackTrace();
		}
	}
	
	private static final Properties	properties;
	static
	{
		Path configData = PathUtils.CONFIG
				.resolve("iidmNames.properties");
		properties = new Properties();
		try (InputStream inputStream = Files.newInputStream(configData))
		{
			properties.load(inputStream);
		}
		catch (IOException e)
		{
			LOG.error("Reading init results 2 sim inputs references");
			e.printStackTrace();
		}
	}

	private static enum OmegaRefModel
	{
		COMPONENT, REAL_OUTPUT, CONSTANT_VALUE, UNKNOWN, NO_MODEL
	};

	private final Path								modelicaFile;
	private final Path								modelicaInitPath;
	private final Path								ddrLocation;
	private final boolean							inferringInitializationModels;
	private final DynamicDataRepositoryDydFiles		ddr;
	private final ModelicaSimulationFinalResults	fakeInitializationResults;
	private final AssociationsDiscoverer			associations;
	private final Set<Model>						addedModels;
	private OmegaRefModel							omegaRefModel;
}
