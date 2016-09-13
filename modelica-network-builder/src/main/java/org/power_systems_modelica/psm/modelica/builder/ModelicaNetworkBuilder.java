package org.power_systems_modelica.psm.modelica.builder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaConnector;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaModelInstantiation;
import org.power_systems_modelica.psm.modelica.ModelicaSystemModel;
import org.power_systems_modelica.psm.modelica.ModelicaTricks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.iidm.network.Bus;
import eu.itesla_project.iidm.network.Connectable;
import eu.itesla_project.iidm.network.EquipmentTopologyVisitor;
import eu.itesla_project.iidm.network.Identifiable;
import eu.itesla_project.iidm.network.Network;

public class ModelicaNetworkBuilder
{
	public ModelicaNetworkBuilder(DynamicDataRepository ddr, Network n)
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

	public ModelicaDocument build()
	{
		ModelicaDocument mo = new ModelicaDocument();
		mo.setWithin("");

		// The model for the whole system
		ModelicaSystemModel m = new ModelicaSystemModel(network.getName());

		addParameters(m);
		addDynamicModels(m);

		// TODO post-process resulting Modelica objects
		// TODO omegaRef should be computed as a weighted sum of omega variables of all machines
		// omegaRef = SUM(g.omega * g.SN * g.HIn) for all g in machines
		// m.addParameters(Arrays.asList(new ModelicaParameter(ModelicaType.Real, "omegaRef", "0.0")));

		mo.setSystemModel(m);
		return mo;
	}

	private void addParameters(ModelicaSystemModel m)
	{
		m.addParameters(ddr.getSystemParameters());
	}

	private void addDynamicModels(ModelicaSystemModel m)
	{
		if (network.getBusBreakerView() == null) return;

		// For every equipment in the main connected component of the Network,
		// obtain the list of model instantiations and their equations
		final Set<Identifiable<?>> visited = new HashSet<>(network.getIdentifiables().size());
		final ConnectorResources connectors = new ConnectorResources();
		for (Bus b : network.getBusBreakerView().getBuses())
		{
			if (onlyMainConnectedComponent && !b.isInMainConnectedComponent()) continue;
			ModelicaModel db = ddr.getModelicaModel(b);
			if (db == null) continue;

			addDynamicModel(m, db);
			EquipmentTopologyVisitor visitor = new EquipmentTopologyVisitor()
			{
				@Override
				public void visitEquipment(Connectable e)
				{
					ModelicaModel de = ddr.getModelicaModel(e);
					if (de == null) return;

					if (!visited.contains(e))
					{
						addDynamicModel(m, de);
						visited.add(e);
					}
					try
					{
						addConnection(m, db, de, connectors);
					}
					catch (ConnectorException x)
					{
						LOG.warn(x.getMessage());
					}
				}
			};
			if (onlyMainConnectedComponent) b.visitConnectedEquipments(visitor);
			else b.visitConnectedOrConnectableEquipments(visitor);
		}
	}

	private void addDynamicModel(ModelicaSystemModel system, ModelicaModel m)
	{
		// We solve here potential external references
		// Model instantiation argument values could be referred to external source (the IIDM Network)
		// We solve these references in the context of the current Network and ModelicaModel

		system.addModelInstantiations(resolveReferences(m.getModelInstantiations(), m));
		system.addEquations(m.getEquations());
	}

	private List<ModelicaModelInstantiation> resolveReferences(
			List<ModelicaModelInstantiation> mis0,
			ModelicaModel m)
	{
		List<ModelicaModelInstantiation> mis = mis0
				.stream()
				.map(mi -> resolveReferences(mi, m))
				.collect(Collectors.toList());
		return mis;
	}

	private ModelicaModelInstantiation resolveReferences(
			ModelicaModelInstantiation mi0,
			ModelicaModel m)
	{
		List<ModelicaArgument> args = mi0
				.getArguments()
				.stream()
				.map(a -> resolveReference(a, m))
				.collect(Collectors.toList());
		return new ModelicaModelInstantiation(mi0.getType(), mi0.getName(), args);
	}

	private ModelicaArgument resolveReference(
			ModelicaArgument a0,
			ModelicaModel m)
	{
		ModelicaArgument a = a0;
		if (a0 instanceof ModelicaArgumentReference)
			a = resolveReference(((ModelicaArgumentReference) a0), m);
		return a;
	}

	private ModelicaArgument resolveReference(
			ModelicaArgumentReference a0,
			ModelicaModel m)
	{
		Object value = null;

		ReferenceResolver r = referenceResolvers.get(a0.getDataSource());
		if (r == null)
			LOG.warn("No resolver found for reference to data source {}", a0.getDataSource());
		else value = r.resolveReference(a0.getSourceName(), m);
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
			throw new RuntimeException(msg);
		}

		// FIXME we are always converting returned value to a string
		// we should store the returned object as the ModelicaArgument value,
		// and leave the formatting of this object for the ModelicaFileWriter
		String svalue = value.toString();

		return new ModelicaArgument(a0.getName(), svalue);
	}

	private void addConnection(
			ModelicaSystemModel m,
			ModelicaModel m1,
			ModelicaModel m2,
			ConnectorResources connectors) throws ConnectorException
	{
		ModelicaConnector conn1 = acquireConnector(m1, m2, connectors);
		ModelicaConnector conn2 = acquireConnector(m2, m1, connectors);
		m.addEquation(new ModelicaConnect(conn1.getRef(), conn2.getRef()));
	}

	static class ConnectorResources
	{
		public boolean isUsed(ModelicaConnector conn)
		{
			return usedConnectors.contains(conn.getRef());
		}

		public void use(ModelicaConnector conn)
		{
			usedConnectors.add(conn.getRef());
		}

		Set<String> usedConnectors = new HashSet<>();
	}

	@SuppressWarnings("serial")
	static class ConnectorException extends Exception
	{
		ConnectorException(String message)
		{
			super(message);
		}
	}

	private ModelicaConnector acquireConnector(
			ModelicaModel m,
			ModelicaModel other,
			ConnectorResources connectors) throws ConnectorException
	{
		ModelicaConnector[] conns = ddr.getModelicaConnectors(m);
		ModelicaConnector conn = conns[0];
		if (conns.length > 1) conn = preferredConnector(m, other, conns);
		conn = acquireConnector(conn, connectors);
		if (conn != null) return conn;
		if (conns.length == 1) throw new ConnectorException("unique connector already used");
		conn = otherConnector(conn, conns);
		if (conn == null) throw new ConnectorException("second connector not found");
		conn = acquireConnector(conn, connectors);
		if (conn == null) throw new ConnectorException("second connector already used");
		return conn;
	}

	private ModelicaConnector acquireConnector(
			ModelicaConnector conn,
			ConnectorResources connectors)
	{
		if (conn.isReusable()) return conn;
		if (connectors.isUsed(conn)) return null;
		connectors.use(conn);
		return conn;
	}

	private ModelicaConnector otherConnector(ModelicaConnector conn, ModelicaConnector[] conns)
	{
		return (conn == conns[0] ? conns[1] : conns[0]);
	}

	private ModelicaConnector preferredConnector(
			ModelicaModel m,
			ModelicaModel other,
			ModelicaConnector[] conns)
	{
		return ModelicaTricks.preferredConnector(m, other, conns);
	}

	private final DynamicDataRepository				ddr;
	private final Network							network;
	private final Map<String, ReferenceResolver>	referenceResolvers			= new HashMap<>();
	private boolean									onlyMainConnectedComponent	= false;
	private static final Logger						LOG							= LoggerFactory
			.getLogger(ModelicaNetworkBuilder.class);
}
