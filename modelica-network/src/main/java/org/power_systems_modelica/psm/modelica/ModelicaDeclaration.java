package org.power_systems_modelica.psm.modelica;

import java.util.Collections;
import java.util.List;

public class ModelicaDeclaration implements Annotable
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

	@Override
	public void resetAnnotation()
	{
		annotation.reset();
	}

	@Override
	public Annotation getAnnotation()
	{
		return annotation;
	}

	@Override
	public void addAnnotation(String annotation)
	{
		this.annotation.add(annotation);
	}

	private final String					type;
	private final String					id;
	private final boolean					isAssignment;
	private final Object					value;
	private final List<ModelicaArgument>	arguments;
	private final boolean					isParameter;
	private final Annotation				annotation			= new Annotation(
			DEFAULT_ANNOTATION);

	private static final String				DEFAULT_ANNOTATION	= "Placement(transformation())";
}
