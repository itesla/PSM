package org.power_systems_modelica.psm.modelica;

import java.util.Collections;
import java.util.List;

public class ModelicaDeclaration
{
	// FIXME Very ugly: the two constructors are too similar: List<ModelicaArgument> is an Object

	public ModelicaDeclaration(
			String type,
			String id,
			Object value,
			boolean isParameter,
			Annotation annotation)
	{
		isAssignment = true;
		this.type = type;
		this.id = id;
		this.value = value;
		this.arguments = null;
		this.isParameter = isParameter;
		this.annotation = annotation;
	}

	public ModelicaDeclaration(
			String type,
			String id,
			List<ModelicaArgument> arguments,
			boolean isParameter,
			Annotation annotation)
	{
		isAssignment = false;
		this.type = type;
		this.id = id;
		this.arguments = arguments;
		this.value = null;
		this.isParameter = isParameter;
		this.annotation = annotation;
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

	public Annotation getAnnotation()
	{
		return annotation;
	}

	private final String					type;
	private final String					id;
	private final boolean					isAssignment;
	private final Object					value;
	private final List<ModelicaArgument>	arguments;
	private final boolean					isParameter;
	private final Annotation				annotation;
}
