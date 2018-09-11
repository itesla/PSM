package org.power_systems_modelica.psm.modelica.builder;

/*
 * #%L
 * Modelica network model
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.modelica.Annotation;
import org.power_systems_modelica.psm.modelica.AnnotationItem;
import org.power_systems_modelica.psm.modelica.BaseModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaInterconnection;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaSystemModel;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public abstract class ModelicaBuilder
{
	public abstract boolean haveAllDynamicModelsBeenAdded();

	public Collection<ModelicaModel> getModels()
	{
		return dynamicModelsByStaticId.values();
	}

	protected void createModelicaDocument(String name, String modelicaVersion)
	{
		mo = new ModelicaDocument();
		mo.setWithin("");
		ModelicaSystemModel sys = new ModelicaSystemModel(name);
		mo.setSystemModel(sys);
		String sversion = String.format(MODELICA_VERSION_ANNOTATION, modelicaVersion);
		sys.getAnnotation().addItem(new AnnotationItem(sversion));
		dynamicModelsByStaticId = new HashMap<>();
	}

	protected void createModelicaDocument(String name)
	{
		createModelicaDocument(name, DEFAULT_MODELICA_VERSION);
	}

	protected ModelicaDocument getModelicaDocument()
	{
		return mo;
	}

	protected void setModelicaDocument(ModelicaDocument mo)
	{
		this.mo = mo;
		dynamicModelsByStaticId = ModelicaUtil.groupByNormalizedStaticId(mo);
	}

	protected ModelicaModel getDynamicModelFor(String staticId)
	{
		String nid = ModelicaUtil.normalizedIdentifier(staticId);
		ModelicaModel m = dynamicModelsByStaticId.get(nid);
		return m;
	}

	protected ModelicaModel getSystemModel()
	{
		return dynamicModelsByStaticId.get(ModelicaUtil.getSystemStaticId());
	}

	protected void addDynamicModel(ModelicaModel m)
	{
		Objects.requireNonNull(m.getId());
		Objects.requireNonNull(m.getStaticId());

		// Annotation common to all declarations and equations of this dynamic model
		AnnotationItem ref = AnnotationItem.annotatePsmRef(m.getId(), m.getStaticId());

		m.getDeclarations().forEach(d -> Annotation.annotate(d, ref));
		m.getEquations().forEach(eq -> Annotation.annotate(eq, ref));

		// We solve here potential external references
		// Argument values in the declarations could be referred to external source (the IIDM Network)
		// We solve these references in the context of the current Network and ModelicaModel
		ModelicaSystemModel system = mo.getSystemModel();
		system.addDeclarations(resolveReferences(m.getDeclarations(), m));
		system.addEquations(m.getEquations());

		// Information about connectors are put as annotations in the system model
		Annotation sa = system.getAnnotation();
		if (m.getInterconnections() != null && m.getInterconnections().length > 0)
		{
			sa.addItems(Arrays.asList(m.getInterconnections()).stream()
					.map(c -> AnnotationItem.annotatePsmInterconnection(c, ref))
					.collect(Collectors.toList()));
		}

		// FIXME When adding we should be merging declarations and equations
		// (add fault to a bus increases the dynamic model of the bus, is not a substitution)
		// problem: the dynamic model of the bus will have two connectors with pin "p" after adding a fault
		// to solve this, the pin for the bus fault could be declared as "used" (not "reusable")
		// in general, only pins of buses are "re-usables" ???
		String id = ModelicaUtil.normalizedIdentifier(m.getStaticId());
		if (!dynamicModelsByStaticId.containsKey(id))
			dynamicModelsByStaticId.put(ModelicaUtil.normalizedIdentifier(m.getStaticId()), m);
	}

	protected void removeDynamicModel(ModelicaModel m)
	{
		// First remove previous declarations and internal connections related to the dynamic model of this static element
		mo.getSystemModel().removeEquations(m.getEquations());
		mo.getSystemModel().removeDeclarations(m.getDeclarations());
		mo.getSystemModel().getAnnotation().removeItems(m.getAnnotation().getItems());

		// Also remove all system annotations that are related to this dynamic model identifier
		String mid = m.getId();
		Set<AnnotationItem> sas = mo.getSystemModel().getAnnotation().getItems().stream()
				.filter(a -> a.isPsmAnnotation())
				.filter(a -> a.isPsmRefAnnotation())
				.filter(a -> a.getId().equals(mid))
				.collect(Collectors.toSet());
		mo.getSystemModel().getAnnotation().removeItems(sas);

		// Also identify and remove previous interconnections with the rest of the system
		String nstaticId = ModelicaUtil.normalizedIdentifier(m.getStaticId());
		List<ModelicaEquation> allInterconnections = ModelicaUtil
				.getInterconnections(dynamicModelsByStaticId);
		List<ModelicaEquation> interconnections = allInterconnections.stream()
				.filter(eq -> {
					ModelicaConnect eqc = (ModelicaConnect) eq;
					return ModelicaUtil.getNormalizedStaticId(eqc, 1).equals(nstaticId)
							|| ModelicaUtil.getNormalizedStaticId(eqc, 2).equals(nstaticId);
				})
				.collect(Collectors.toList());
		mo.getSystemModel().removeEquations(interconnections);

		dynamicModelsByStaticId.remove(m.getStaticId());
	}

	protected void addInterconnections(Collection<UnresolvedRef> unresolved)
	{
		// We process all models sorted by id to ensure we assign items in arrays of connections
		// in the same order we have seen in original Modelica files (omegaRef)
		// Instead of:
		// getModels().stream().forEach(m -> addInterconnections(m));
		// We do:
		List<ModelicaModel> models = new ArrayList<>(getModels());
		Collections.sort(models, Comparator.comparing(ModelicaModel::getId));
		models.stream().forEach(m -> addInterconnections(m, unresolved));
	}

	protected void addInterconnections(
			ModelicaModel m,
			Collection<UnresolvedRef> unresolved)
	{
		mo.getSystemModel().addEquations(buildInterconnections(m, unresolved));
	}

	protected List<ModelicaEquation> buildInterconnections(
			ModelicaModel m,
			Collection<UnresolvedRef> unresolved)
	{
		List<ModelicaEquation> connections = new ArrayList<>();
		for (ModelicaInterconnection c : m.getInterconnections())
		{
			if (c.isLocal2Target())
			{
				ModelicaInterconnection tc;
				tc = resolveTarget(c.getTargetModel(), c.getTargetName(), m, mo);
				if (tc == null)
				{
					unresolved.add(new UnresolvedRef(
							"interconnection",
							m.getId(),
							c.getTargetModel(),
							c.getTargetName()));
					continue;
				}

				ModelicaConnect eqc = buildConnection(tc, c);
				// For resolved targets we have stored the proper static identifier in target connector
				annotateStaticIdsInConnection(eqc, tc.getStaticId(), m.getStaticId());
				connections.add(eqc);
			}
			else if (c.isDoubleTarget())
			{
				ModelicaInterconnection tc1, tc2;
				tc1 = resolveTarget(c.getTargetModel(), c.getTargetName(), m, mo);
				tc2 = resolveTarget(c.getTargetModel2(), c.getTargetName2(), m, mo);
				if (tc1 == null) unresolved.add(new UnresolvedRef(
						"interconnection",
						m.getId(),
						c.getTargetModel(),
						c.getTargetName()));
				if (tc2 == null) unresolved.add(new UnresolvedRef(
						"interconnection",
						m.getId(),
						c.getTargetModel2(),
						c.getTargetName2()));
				if (tc1 == null || tc2 == null) continue;

				ModelicaConnect eqc = buildConnection(tc1, tc2);
				annotateStaticIdsInConnection(eqc, tc1.getStaticId(), tc2.getStaticId());
				connections.add(eqc);
			}
		}
		return connections;
	}

	private static ModelicaConnect buildConnection(
			ModelicaInterconnection c1,
			ModelicaInterconnection c2)
	{
		String ref1 = ModelicaUtil.idvar2ref(c1.getComponentId(), c1.getComponentVar());
		String ref2 = ModelicaUtil.idvar2ref(c2.getComponentId(), c2.getComponentVar());
		return new ModelicaConnect(ref1, ref2);
	}

	private static void annotateStaticIdsInConnection(ModelicaConnect eqc, String id1, String id2)
	{
		if (id1 == null || id2 == null) return;
		AnnotationItem ids = AnnotationItem.annotatePsmRefStaticId12(id1, id2);
		if (eqc.getAnnotation() == null)
			eqc.setAnnotation(new Annotation(ids));
		else eqc.getAnnotation().addItem(ids);
	}

	protected ModelicaInterconnection resolveTarget(
			String targetModel,
			String targetName,
			ModelicaModel m,
			ModelicaDocument mo)
	{
		if (targetModel == null)
		{
			LOG.warn("No model was specified for interconnection with data source {}", targetModel);
			return null;
		}
		// The resolver for target of interconnections is the dynamic network
		String dataSource = "DYNN";
		ReferenceResolver r = referenceResolvers.get(dataSource);
		if (r == null)
		{
			LOG.warn("No resolver found for connector with data source {}", dataSource);
			return null;
		}
		return r.resolveConnectionTarget(targetModel, targetName, m);
	}

	protected void registerResolver(String dataSource, ReferenceResolver resolver)
	{
		referenceResolvers.put(dataSource, resolver);
	}

	protected void removeResolver(String dataSource)
	{
		referenceResolvers.remove(dataSource);
	}

	protected List<BaseModelicaDeclaration> resolveReferences(
			List<BaseModelicaDeclaration> ds0,
			ModelicaModel m)
	{
		List<BaseModelicaDeclaration> ds = ds0
				.stream()
				.map(d -> resolveReferences(d, m))
				.collect(Collectors.toList());
		return ds;
	}

	private BaseModelicaDeclaration resolveReferences(
			BaseModelicaDeclaration d,
			ModelicaModel m)
	{
		// TODO consider if we have to resolveReferences only in arguments or also in assignments
		if (d.isAssignment()) return d;

		List<ModelicaArgument> args = d.getArguments().stream()
				.map(a -> resolveReference(a, m, d))
				.collect(Collectors.toList());
		ModelicaDeclaration d1 = new ModelicaDeclaration(
				d.getType(),
				d.getId(),
				args,
				d.isParameter(),
				d.getAnnotation());
		// Preserve origin
		d1.setOrigin(d.getOrigin());
		return d1;
	}

	protected ModelicaArgument resolveReference(
			ModelicaArgument a0,
			ModelicaModel m,
			BaseModelicaDeclaration d)
	{
		ModelicaArgument a = a0;
		if (a0 instanceof ModelicaArgumentReference)
			a = resolveReference(((ModelicaArgumentReference) a0), m, d);
		return a;
	}

	private ModelicaArgument resolveReference(
			ModelicaArgumentReference a0,
			ModelicaModel m,
			BaseModelicaDeclaration d)
	{
		Object value = null;

		ReferenceResolver r = referenceResolvers.get(a0.getDataSource());
		if (r == null)
		{
			LOG.warn("No resolver found for reference to data source {}", a0.getDataSource());
			return null;
		}

		try
		{
			value = r.resolveReference(a0, m, d);
		}
		catch (IncompleteReferenceException e)
		{
			// The reference can not yet be resolved,
			// but maybe will be resolved in a later phase,
			// We maintain it as a reference
			return a0;
		}
		catch (UnresolvedReferenceException e)
		{
			String msg = new StringBuilder()
					.append("could not resolve reference ")
					.append(a0.getSourceName())
					.append(" in data source ")
					.append(a0.getDataSource())
					.append(" for model with staticId ")
					.append(m.getStaticId())
					.toString();
			LOG.error(msg);
			throw new RuntimeException(msg);
		}
		catch (ModelicaArgumentReferenceException e)
		{
			throw new RuntimeException(e);
		}
		return new ModelicaArgument(a0.getName(), value);
	}

	private ModelicaDocument						mo;
	protected Map<String, ModelicaModel>			dynamicModelsByStaticId;
	private final Map<String, ReferenceResolver>	referenceResolvers			= new HashMap<>();

	private static final String						DEFAULT_MODELICA_VERSION	= "3.2.1";
	private static final String						MODELICA_VERSION_ANNOTATION	= "uses(Modelica(version=\"%s\"))";
	private static final Logger						LOG							= LoggerFactory
			.getLogger(ModelicaBuilder.class);
}
