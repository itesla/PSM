package org.power_systems_modelica.psm.ddr.dyd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.power_systems_modelica.psm.ddr.Stage;

public class Model
{
	public Model(String id)
	{
		Objects.requireNonNull(id);
		this.id = id;
	}

	public String getId()
	{
		return id;
	}

	public void setStage(Stage stage)
	{
		this.stage = stage;
	}

	public Stage getStage()
	{
		return stage;
	}

	public List<Component> getComponents()
	{
		return Collections.unmodifiableList(components);
	}

	// Internal connections between components
	public List<Connection> getConnections()
	{
		return Collections.unmodifiableList(connections);
	}

	// External connectors offered by the model
	public List<Connector> getConnectors()
	{
		return Collections.unmodifiableList(connectors);
	}

	public void addComponents(Collection<Component> components)
	{
		this.components.addAll(components);
	}

	public void addComponent(Component c)
	{
		components.add(c);
	}

	public void addConnections(Collection<Connection> connections)
	{
		this.connections.addAll(connections);
	}

	public void addConnection(Connection c)
	{
		connections.add(c);
	}

	public void addConnector(Connector c)
	{
		connectors.add(c);
	}

	public void addConnectors(List<Connector> c)
	{
		connectors.addAll(c);
	}

	private final String			id;
	private Stage					stage		= Stage.SIMULATION;
	private final List<Component>	components	= new ArrayList<>();
	private final List<Connection>	connections	= new ArrayList<>();
	private final List<Connector>	connectors	= new ArrayList<>();
}
