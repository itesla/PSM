package org.power_systems_modelica.psm.modelica;

import java.util.Objects;

public class ModelicaInterconnection
{
	public static ModelicaInterconnection create(
			String name,
			String declarationId, String declarationVar,
			String targetModel, String targetName)
	{
		if (targetModel == null && targetName == null)
			return new ModelicaInterconnection(name, declarationId, declarationVar);
		else
			return new ModelicaInterconnection(
					declarationId, declarationVar,
					targetModel, targetName);
	}

	// A declaration of a point of interconnection where others can connect (a sink, a target)
	public ModelicaInterconnection(String name, String declarationId, String declarationVar)
	{
		Objects.requireNonNull(name);
		// componentId may be null
		Objects.requireNonNull(declarationVar);

		this.name = name;
		this.declarationId = declarationId;
		this.declarationVar = declarationVar;

		this.targetModel = null;
		this.targetName = null;
	}

	// A declaration of a point of interconnection that specifies a target (a source, an arrow)
	public ModelicaInterconnection(
			String declarationId, String declarationVar,
			String targetModel, String targetName)
	{
		// componentId may be null
		Objects.requireNonNull(declarationVar);
		Objects.requireNonNull(targetModel);
		Objects.requireNonNull(targetName);

		this.name = null;

		this.declarationId = declarationId;
		this.declarationVar = declarationVar;
		this.targetModel = targetModel;
		this.targetName = targetName;
	}

	public String getName()
	{
		return name;
	}

	public String getComponentId()
	{
		return declarationId;
	}

	public String getComponentVar()
	{
		return declarationVar;
	}

	public String getTargetModel()
	{
		return targetModel;
	}

	public String getTargetName()
	{
		return targetName;
	}

	public void setStaticId(String staticId)
	{
		this.staticId = staticId;
	}

	public String getStaticId()
	{
		return staticId;
	}

	public int nextConnectionArrayItem()
	{
		return ++connectionArrayCurrentItem;
	}

	private final String	name;
	private final String	declarationId;
	private final String	declarationVar;
	private final String	targetModel;
	private final String	targetName;

	private String			staticId;
	private int				connectionArrayCurrentItem	= 0;
}
