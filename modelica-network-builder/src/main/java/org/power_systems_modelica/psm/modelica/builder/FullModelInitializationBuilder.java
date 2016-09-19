package org.power_systems_modelica.psm.modelica.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaSystemModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.iidm.network.Bus;
import eu.itesla_project.iidm.network.Connectable;
import eu.itesla_project.iidm.network.EquipmentTopologyVisitor;
import eu.itesla_project.iidm.network.Identifiable;
import eu.itesla_project.iidm.network.Network;

public class FullModelInitializationBuilder
{
	public FullModelInitializationBuilder(DynamicDataRepository ddr, Network n)
	{
		this.ddr = ddr;
		this.network = n;
		registerResolver("IIDM", new IidmReferenceResolver(network));
	}

	public void setOnlyMain(boolean onlyMain)
	{
		this.onlyMainConnectedComponent = onlyMain;
	}

	public void registerResolver(String dataSource, ReferenceResolver resolver)
	{
		referenceResolvers.put(dataSource, resolver);
	}

	public Collection<ModelicaDocument> buildModelicaDocuments()
	{
		if (network.getBusBreakerView() == null) return null;
		Collection<ModelicaDocument> mos = new ArrayList<ModelicaDocument>();

		// For every equipment that has initialization components build its initialization Modelica document
		final Set<Identifiable<?>> visited = new HashSet<>(network.getIdentifiables().size());
		for (Bus b : network.getBusBreakerView().getBuses())
		{
			if (onlyMainConnectedComponent && !b.isInMainConnectedComponent()) continue;

			addInitialization(b, mos);
			EquipmentTopologyVisitor visitor = new EquipmentTopologyVisitor()
			{
				@Override
				public void visitEquipment(Connectable<?> e)
				{
					if (!visited.contains(e))
					{
						addInitialization(e, mos);
						visited.add(e);
					}
				}
			};
			if (onlyMainConnectedComponent) b.visitConnectedEquipments(visitor);
			else b.visitConnectedOrConnectableEquipments(visitor);
		}
		return mos;
	}

	private void addInitialization(Identifiable<?> e, Collection<ModelicaDocument> mos)
	{
		ModelicaDocument mo = null;
		ModelicaModel de = ddr.getModelicaInitializationModel(e);
		if (de != null) mo = buildModelicaInitializationDocument(de);
		if (mo != null) mos.add(mo);
	}

	private ModelicaDocument buildModelicaInitializationDocument(ModelicaModel m)
	{
		ModelicaDocument mo = new ModelicaDocument();
		mo.setWithin("");
		ModelicaSystemModel ms = new ModelicaSystemModel(m.getStaticId()); // FIXME or m.getName(); ???
		ms.addDeclarations(ddr.getSystemDeclarations());

		// We solve here potential external references
		// Argument values in the declarations could be referred to external source (the IIDM Network)
		// We solve these references in the context of the current Network and ModelicaModel

		ms.addDeclarations(resolveReferences(m.getDeclarations(), m));
		ms.addEquations(m.getEquations());
		return mo;
	}

	private List<ModelicaDeclaration> resolveReferences(
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
		// TODO consider i we have to resolveReferences only in arguments or also in assignments
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

		// FIXME we are always converting returned value to a string
		// we should store the returned object as the ModelicaArgument value,
		// and leave the formatting of this object for the ModelicaFileWriter
		String svalue = value.toString();

		return new ModelicaArgument(a0.getName(), svalue);
	}

	private final DynamicDataRepository				ddr;
	private final Network							network;
	private final Map<String, ReferenceResolver>	referenceResolvers			= new HashMap<>();
	private boolean									onlyMainConnectedComponent	= false;
	private static final Logger						LOG							= LoggerFactory
			.getLogger(FullModelInitializationBuilder.class);
}
