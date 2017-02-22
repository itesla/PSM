package org.power_systems_modelica.psm.modelica;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ModelicaDeclaration implements Annotable
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
		// No default annotation for assignments
		this.annotation = annotation;
	}

	public ModelicaDeclaration(
			String type,
			String id,
			List<ModelicaArgument> arguments,
			boolean isParameter,
			Annotation annotation)
	{
		Objects.requireNonNull(arguments);
		isAssignment = false;
		this.type = type;
		this.id = id;
		this.arguments = arguments;
		this.value = null;
		this.isParameter = isParameter;
		if (annotation == null) this.annotation = new Annotation(DEFAULT_ANNOTATION);
		else this.annotation = annotation;
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

	public void replaceArguments(List<ModelicaArgument> args1)
	{
		this.arguments = args1;
	}

	public boolean containsAnyReference()
	{
		return arguments.stream().anyMatch(a -> a instanceof ModelicaArgumentReference);
	}

	@Override
	public Annotation getAnnotation()
	{
		return annotation;
	}

	@Override
	public void setAnnotation(Annotation annotation)
	{
		this.annotation = annotation;
	}

	public void setOrigin(String origin)
	{
		this.origin = origin;
	}

	public String getOrigin()
	{
		return origin;
	}

	private final String			type;
	private final String			id;
	private final boolean			isAssignment;
	private final Object			value;
	private final boolean			isParameter;

	private List<ModelicaArgument>	arguments;
	private Annotation				annotation;

	private String					origin;

	private static final String		DEFAULT_ANNOTATION	= "Placement(transformation())";
}
