package org.power_systems_modelica.psm.ddr.dyd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.power_systems_modelica.psm.ddr.Stage;
import org.power_systems_modelica.psm.ddr.dyd.equations.Equation;

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

	// External points of interconnection
	public List<Interconnection> getInterconnections()
	{
		return Collections.unmodifiableList(interconnections);
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

	public void addConnector(Interconnection c)
	{
		interconnections.add(c);
	}

	public void addInterconnections(List<Interconnection> c)
	{
		interconnections.addAll(c);
	}

	// Equations that are not connects

	public List<Equation> getOtherEquations()
	{
		return otherEquations;
	}

	public void addOtherEquation(Equation eq)
	{
		otherEquations.add(eq);
	}

	// The origin of this model (where did it came from)

	public Model stampOrigin(String origin)
	{
		this.origin = origin;
		return this;
	}

	public String getOrigin()
	{
		return origin;
	}

	private final String				id;
	private Stage						stage				= Stage.SIMULATION;
	private final List<Component>		components			= new ArrayList<>();
	private final List<Connection>		connections			= new ArrayList<>();
	private final List<Interconnection>	interconnections	= new ArrayList<>();
	private final List<Equation>		otherEquations		= new ArrayList<>();

	private String						origin				= "unknown";
}
