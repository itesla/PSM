package org.power_systems_modelica.psm.modelica;

import java.util.Collections;
import java.util.List;

public class ModelicaModelInstantiation
{
	public ModelicaModelInstantiation(String type, String name,
			List<ModelicaArgument> arguments)
	{
		this.type = type;
		this.name = name;
		this.arguments = arguments;
		this.annotation = DEFAULT_ANNOTATION;
	}

	public String getType()
	{
		return type;
	}

	public String getName()
	{
		return name;
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

	@Override
	public String toString()
	{
		return String.format("type=%s, name=%s, arguments=%s, annotation=%s", type, name, arguments,
				annotation);
	}

	private final String								type;
	private final String								name;
	private final List<ModelicaArgument>	arguments;
	private final String								annotation;

	private static final String							DEFAULT_ANNOTATION	= "Placement(transformation())";
}
