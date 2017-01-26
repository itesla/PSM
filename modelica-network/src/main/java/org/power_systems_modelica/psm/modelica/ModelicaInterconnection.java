package org.power_systems_modelica.psm.modelica;

public class ModelicaInterconnection
{
	public ModelicaInterconnection(
			String name,
			String declarationId, String declarationVar,
			String targetModel, String targetName,
			String targetModel2, String targetName2)
	{
		this.name = name;
		this.declarationId = declarationId;
		this.declarationVar = declarationVar;
		this.targetModel = targetModel;
		this.targetName = targetName;
		this.targetModel2 = targetModel2;
		this.targetName2 = targetName2;
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

	public String getTargetModel2()
	{
		return targetModel2;
	}

	public String getTargetName2()
	{
		return targetName2;
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

	public boolean isLocal2Target()
	{
		return declarationVar != null && targetName != null;
	}

	public boolean isDoubleTarget()
	{
		return targetName != null && targetName2 != null;
	}

	private final String	name;
	private final String	declarationId;
	private final String	declarationVar;
	private final String	targetModel;
	private final String	targetName;
	private final String	targetModel2;
	private final String	targetName2;

	private String			staticId;
	private int				connectionArrayCurrentItem	= 0;
}
