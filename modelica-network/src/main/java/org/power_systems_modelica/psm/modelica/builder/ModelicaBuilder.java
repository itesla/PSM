package org.power_systems_modelica.psm.modelica.builder;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.power_systems_modelica.psm.modelica.Annotation;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaConnector;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaSystemModel;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModelicaBuilder
{
	protected void createModelicaDocument(String name)
	{
		mo = new ModelicaDocument();
		mo.setWithin("");
		ModelicaSystemModel sys = new ModelicaSystemModel(name);
		mo.setSystemModel(sys);
		dynamicModelsByStaticId = new HashMap<>();
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

	protected Collection<ModelicaModel> getModels()
	{
		return dynamicModelsByStaticId.values();
	}

	protected ModelicaModel getDynamicModelFor(String staticId)
	{
		return dynamicModelsByStaticId.get(ModelicaUtil.normalizedIdentifier(staticId));
	}

	protected void addDynamicModel(ModelicaModel m)
	{
		// Annotation common to all declarations and equations of this dynamic model
		String refs = Annotation.writeIdStaticId(m.getName(), m.getStaticId());

		m.getDeclarations().forEach(d -> {
			if (d.getAnnotation() == null)
				d.setAnnotation(new Annotation(refs));
			else d.getAnnotation().addItem(refs);
		});
		m.getEquations().forEach(eq -> {
			if (eq.getAnnotation() == null)
				eq.setAnnotation(new Annotation(refs));
			else eq.getAnnotation().addItem(refs);
		});

		// We solve here potential external references
		// Argument values in the declarations could be referred to external source (the IIDM Network)
		// We solve these references in the context of the current Network and ModelicaModel
		ModelicaSystemModel system = mo.getSystemModel();
		system.addDeclarations(resolveReferences(m.getDeclarations(), m));
		system.addEquations(m.getEquations());

		// Information about connectors are put as annotations in the output model
		Annotation a = new Annotation(refs);
		if (m.getConnectors() != null && m.getConnectors().length > 0)
			a.addItem(Annotation.writeConnectors(Arrays.asList(m.getConnectors())));
		system.addAnnotation(a);

		// FIXME When adding we should be merging declarations and equations
		// (add fault to a bus increases the dynamic model of the bus, is not a substitution)
		// problem: the dynamic model of the bus will have two connectors with pin "p" after adding a fault
		// to solve this, the pin for the bus fault could be declared as "used" (not "reusable")
		// in general, only pins of buses are "reusables" ???
		dynamicModelsByStaticId.put(m.getStaticId(), m);
	}

	protected void removeDynamicModel(ModelicaModel m)
	{
		// First remove previous declarations and internal connections related to the dynamic model of this static element
		mo.getSystemModel().removeEquations(m.getEquations());
		mo.getSystemModel().removeDeclarations(m.getDeclarations());
		mo.getSystemModel().removeAnnotations(m.getAnnotations());

		// Also identify and remove previous interconnections with the rest of the system
		String staticId = m.getStaticId();
		List<ModelicaEquation> allInterconnections = ModelicaUtil
				.getInterconnections(dynamicModelsByStaticId);
		List<ModelicaEquation> interconnections = allInterconnections.stream()
				.filter(eq -> {
					ModelicaConnect eqc = (ModelicaConnect) eq;
					return ModelicaUtil.getStaticId(eqc, 1).equals(staticId)
							|| ModelicaUtil.getStaticId(eqc, 2).equals(staticId);
				})
				.collect(Collectors.toList());
		mo.getSystemModel().removeEquations(interconnections);

		dynamicModelsByStaticId.remove(m.getStaticId());
	}

	protected void addInterconnections()
	{
		getModels().stream().forEach(m -> addInterconnections(m));
	}

	protected void addInterconnections(ModelicaModel m)
	{
		mo.getSystemModel().addEquations(buildInterconnections(m));
	}

	protected List<ModelicaEquation> buildInterconnections(ModelicaModel m)
	{
		Stream<ModelicaConnector> sourceConnectors = Stream.of(m.getConnectors());
		List<ModelicaEquation> targetConnectors = sourceConnectors
				.map(sc -> sc.getTarget()
						.flatMap(t -> resolveTarget(t, m, mo))
						.map(tc -> {
							ModelicaConnect eqc = new ModelicaConnect(tc.getRef(), sc.getRef());
							String id2 = m.getStaticId();
							// For resolved targets we have stored the proper static identifier
							String id1 = tc.getStaticId();
							if (id1 != null && id2 != null)
							{
								String ids = Annotation.writeStaticId12(id1, id2);
								if (eqc.getAnnotation() == null)
									eqc.setAnnotation(new Annotation(ids));
								else eqc.getAnnotation().addItem(ids);
							}
							return eqc;
						}))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
		return targetConnectors;
	}

	protected Optional<ModelicaConnector> resolveTarget(
			String target,
			ModelicaModel m,
			ModelicaDocument mo)
	{
		String atarget[] = target.split(":");
		String resolver = atarget[0];
		String item = atarget[1];
		String pin = atarget.length > 2 ? atarget[2] : "";

		ReferenceResolver r = referenceResolvers.get(resolver);
		if (r == null)
		{
			LOG.warn("No resolver found for connection target on data source {}", resolver);
			return Optional.empty();
		}

		return r.resolveConnectionTarget(item, pin, m);
	}

	protected void registerResolver(String dataSource, ReferenceResolver resolver)
	{
		referenceResolvers.put(dataSource, resolver);
	}

	protected void removeResolver(String dataSource)
	{
		referenceResolvers.remove(dataSource);
	}

	protected List<ModelicaDeclaration> resolveReferences(
			List<ModelicaDeclaration> ds0,
			ModelicaModel m)
	{
		List<ModelicaDeclaration> ds = ds0
				.stream()
				.map(d -> resolveReferences(d, m))
				.collect(Collectors.toList());
		return ds;
	}

	private ModelicaDeclaration resolveReferences(
			ModelicaDeclaration d,
			ModelicaModel m)
	{
		// TODO consider if we have to resolveReferences only in arguments or also in assignments
		if (d.isAssignment()) return d;

		List<ModelicaArgument> args = d.getArguments().stream()
				.map(a -> resolveReference(a, m, d))
				.collect(Collectors.toList());
		return new ModelicaDeclaration(
				d.getType(),
				d.getId(),
				args,
				d.isParameter(),
				d.getAnnotation());
	}

	private ModelicaArgument resolveReference(
			ModelicaArgument a0,
			ModelicaModel m,
			ModelicaDeclaration d)
	{
		ModelicaArgument a = a0;
		if (a0 instanceof ModelicaArgumentReference)
			a = resolveReference(((ModelicaArgumentReference) a0), m, d);
		return a;
	}

	private ModelicaArgument resolveReference(
			ModelicaArgumentReference a0,
			ModelicaModel m,
			ModelicaDeclaration d)
	{
		Object value = null;

		ReferenceResolver r = referenceResolvers.get(a0.getDataSource());
		if (r == null)
		{
			LOG.warn("No resolver found for reference to data source {}", a0.getDataSource());
			return null;
		}

		value = r.resolveReference(a0.getSourceName(), m, d);
		if (value == null)
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
		return new ModelicaArgument(a0.getName(), value);
	}

	private ModelicaDocument						mo;
	private Map<String, ModelicaModel>				dynamicModelsByStaticId;
	private final Map<String, ReferenceResolver>	referenceResolvers	= new HashMap<>();

	private static final Logger						LOG					= LoggerFactory
			.getLogger(ModelicaBuilder.class);
}
