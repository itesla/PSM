package org.power_systems_modelica.psm.modelica.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaConnector;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaSystemModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModelicaBuilder
{
	protected void addDynamicModel(ModelicaModel m, ModelicaDocument mo)
	{
		// We solve here potential external references
		// Argument values in the declarations could be referred to external source (the IIDM Network)
		// We solve these references in the context of the current Network and ModelicaModel
		ModelicaSystemModel system = mo.getSystemModel();
		system.addDeclarations(resolveReferences(m.getDeclarations(), m));
		system.addEquations(m.getEquations());
	}

	protected void addConnections(ModelicaModel m, ModelicaDocument mo)
	{
		mo.getSystemModel().addEquations(buildConnections(m, mo));
	}

	protected List<ModelicaEquation> buildConnections(ModelicaModel m, ModelicaDocument mo)
	{
		// Add connections with the rest of the system
		List<ModelicaEquation> connections = new ArrayList<>();
		Stream.of(m.getConnectors())
				.forEach(c -> c.getTarget()
						.map(t -> resolveTarget(t, m, mo))
						.ifPresent(ct -> connections
								.add(new ModelicaConnect(ct.getRef(), c.getRef()))));
		return connections;
	}

	private ModelicaConnector resolveTarget(String target, ModelicaModel m, ModelicaDocument mo)
	{
		String atarget[] = target.split(":");
		String resolver = atarget[0];
		String item = atarget[1];
		String pin = atarget.length > 2 ? atarget[2] : "";

		ReferenceResolver r = referenceResolvers.get(resolver);
		if (r == null)
		{
			LOG.warn("No resolver found for connection target of type {}", r);
			return null;
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

	private final Map<String, ReferenceResolver>	referenceResolvers	= new HashMap<>();
	private static final Logger						LOG					= LoggerFactory
			.getLogger(ModelicaBuilder.class);
}
