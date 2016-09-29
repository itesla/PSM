package org.power_systems_modelica.psm.modelica.builder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.iidm.network.Network;

public class ModelicaBuilder
{
	public ModelicaBuilder(DynamicDataRepository ddr, Network n)
	{
		this.ddr = ddr;
		this.network = n;
		registerResolver("IIDM", new IidmReferenceResolver(network));
	}

	public Network getNetwork()
	{
		return network;
	}

	public boolean isOnlyMainConnectedComponent()
	{
		return onlyMainConnectedComponent;
	}

	public void setOnlyMainConnectedComponent(boolean onlyMain)
	{
		this.onlyMainConnectedComponent = onlyMain;
	}

	public DynamicDataRepository getDdr()
	{
		return ddr;
	}

	protected void registerResolver(String dataSource, ReferenceResolver resolver)
	{
		referenceResolvers.put(dataSource, resolver);
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

		List<ModelicaArgument> args = d
				.getArguments()
				.stream()
				.map(a -> resolveReference(a, m, d))
				.collect(Collectors.toList());
		return new ModelicaDeclaration(d.getType(), d.getId(), args, d.isParameter());
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

	private final DynamicDataRepository				ddr;
	private final Network							network;
	private final Map<String, ReferenceResolver>	referenceResolvers			= new HashMap<>();
	private boolean									onlyMainConnectedComponent	= false;
	private static final Logger						LOG							= LoggerFactory
			.getLogger(ModelicaBuilder.class);
}
