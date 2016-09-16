package org.power_systems_modelica.psm.modelica;

import java.util.Collections;
import java.util.List;

public class ModelicaDeclaration
{
	public ModelicaDeclaration(
			String type,
			String id,
			Object value,
			boolean isParameter)
	{
		isAssignment = true;
		this.type = type;
		this.id = id;
		this.value = value;
		this.arguments = null;
		this.isParameter = isParameter;
		this.annotation = DEFAULT_ANNOTATION;
	}

	public ModelicaDeclaration(
			String type,
			String id,
			List<ModelicaArgument> arguments,
			boolean isParameter)
	{
		isAssignment = false;
		this.type = type;
		this.id = id;
		this.arguments = arguments;
		this.value = null;
		this.isParameter = isParameter;
		this.annotation = DEFAULT_ANNOTATION;
	}

	public String getType()
	{
		return type;
	}

	public String getId()
	{
		return id;
	}

	public boolean isParameter()
	{
		return isParameter;
	}

	public boolean isAssignment()
	{
		return isAssignment;
	}

	public Object getValue()
	{
		return value;
	}

	public List<ModelicaArgument> getArguments()
	{
		if (arguments != null) return Collections.unmodifiableList(arguments);
		return null;
	}

	public String getAnnotation()
	{
		return annotation;
	}

	private final String					type;
	private final String					id;
	private final boolean					isAssignment;
	private final Object					value;
	private final List<ModelicaArgument>	arguments;
	private final boolean					isParameter;
	private final String					annotation;

	private static final String				DEFAULT_ANNOTATION	= "Placement(transformation())";
}
