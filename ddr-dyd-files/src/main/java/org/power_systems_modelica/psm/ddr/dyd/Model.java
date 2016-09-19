package org.power_systems_modelica.psm.ddr.dyd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Model
{
	public Model(String id, String staticId)
	{
		this.id = id;
		this.staticId = staticId;
	}

	public String getId()
	{
		return id;
	}

	public String getStaticId()
	{
		return staticId;
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
	private final String			staticId;
	private final List<Component>	components	= new ArrayList<>();
	private final List<Connection>	connections	= new ArrayList<>();
	private final List<Connector>	connectors	= new ArrayList<>();
}
