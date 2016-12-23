package org.power_systems_modelica.psm.ddr.dyd;

import java.util.Objects;

public class Interconnection
{
	// A declaration of a point of interconnection where others can connect (a sink, a target)
	public Interconnection(String name, String componentId, String componentVar)
	{
		Objects.requireNonNull(name);
		// componentId may be null (the variable is global)
		Objects.requireNonNull(componentVar);

		this.name = name;
		this.componentId = componentId;
		this.componentVar = componentVar;

		this.targetModel = null;
		this.targetName = null;
	}

	// A declaration of a point of interconnection that specifies a target (a source, an arrow)
	public Interconnection(String componentId, String componentVar,
			String targetModel, String targetName)
	{
		// componentId may be null 
		Objects.requireNonNull(componentVar);
		Objects.requireNonNull(targetModel);
		Objects.requireNonNull(targetName);

		this.name = null;

		this.componentId = componentId;
		this.componentVar = componentVar;
		this.targetModel = targetModel;
		this.targetName = targetName;
	}

	public String getName()
	{
		return name;
	}

	public String getComponentId()
	{
		return componentId;
	}

	public String getComponentVar()
	{
		return componentVar;
	}

	public String getTargetModel()
	{
		return targetModel;
	}

	public String getTargetName()
	{
		return targetName;
	}

	private final String	name;
	private final String	componentId;
	private final String	componentVar;
	private final String	targetModel;
	private final String	targetName;
}
