package org.power_systems_modelica.psm.modelica.builder;

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
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaConnector;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaSystemModel;
import org.power_systems_modelica.psm.modelica.ModelicaTricks;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.iidm.network.Bus;
import eu.itesla_project.iidm.network.Connectable;
import eu.itesla_project.iidm.network.EquipmentTopologyVisitor;
import eu.itesla_project.iidm.network.Identifiable;
import eu.itesla_project.iidm.network.Network;

public class ModelicaNetworkBuilder
{
	public ModelicaNetworkBuilder(DynamicDataRepository ddr, Network n, ModelicaEngine me)
	{
		this.ddr = ddr;
		this.network = n;
		this.modelicaEngine = me;
		registerResolver("IIDM", new IidmReferenceResolver(network));
	}

	public void setOnlyMain(boolean onlyMain)
	{
		this.onlyMainConnectedComponent = onlyMain;
	}

	public ModelicaDocument build()
	{
		performFullModelInitialization();
		return buildModelicaSystem();
	}

	private void performFullModelInitialization()
	{
		FullModelInitializationBuilder i = new FullModelInitializationBuilder(ddr, network);
		Collection<ModelicaDocument> mos = i.buildModelicaDocuments();
		modelicaEngine.simulate(mos);
		ModelicaSimulationResults mor = modelicaEngine.getSimulationResults();
		InitializationResults results = new InitializationResults(mor);
		InitializationReferenceResolver ir = new InitializationReferenceResolver(results);
		registerResolver("INIT", ir);
	}

	private ModelicaDocument buildModelicaSystem()
	{
		ModelicaDocument mo = new ModelicaDocument();
		mo.setWithin("");

		// The model for the whole system
		ModelicaSystemModel m = new ModelicaSystemModel(network.getName());

		m.addDeclarations(ddr.getSystemDeclarations());
		addDynamicModels(m);

		// TODO post-process resulting Modelica objects
		// TODO omegaRef should be computed as a weighted sum of omega variables of all machines
		// omegaRef = SUM(g.omega * g.SN * g.HIn) for all g in machines
		// m.addParameters(Arrays.asList(new ModelicaParameter(ModelicaType.Real, "omegaRef", "0.0")));

		mo.setSystemModel(m);
		return mo;
	}

	private void addDynamicModels(ModelicaSystemModel m)
	{
		if (network.getBusBreakerView() == null) return;

		// For every equipment in the main connected component of the Network,
		// obtain the list of model declarations and equations

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
				public void visitEquipment(Connectable<?> e)
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
						LOG.warn(
								"could not add connection between bus {} and element {}, reason '{}'",
								b.getId(),
								e.getId(),
								x.getMessage());
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
		// Argument values in the declarations could be referred to external source (the IIDM Network)
		// We solve these references in the context of the current Network and ModelicaModel

		system.addDeclarations(resolveReferences(m.getDeclarations(), m));
		system.addEquations(m.getEquations());
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
		ModelicaConnector[] conns = m.getConnectors();
		if (conns.length == 0) throw new ConnectorException("no connectors found");
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

	private void registerResolver(String dataSource, ReferenceResolver resolver)
	{
		referenceResolvers.put(dataSource, resolver);
	}

	private final DynamicDataRepository				ddr;
	private final Network							network;
	private final ModelicaEngine					modelicaEngine;
	private final Map<String, ReferenceResolver>	referenceResolvers			= new HashMap<>();
	private boolean									onlyMainConnectedComponent	= false;
	private static final Logger						LOG							= LoggerFactory
			.getLogger(ModelicaNetworkBuilder.class);
}
