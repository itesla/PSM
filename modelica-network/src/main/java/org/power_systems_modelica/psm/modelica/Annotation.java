package org.power_systems_modelica.psm.modelica;

public class Annotation
{
	// TODO In the future it will contain a list of items (maybe ModelicaDeclarations)

	public Annotation(String annotation)
	{
		this.annotation = annotation;
	}

	public boolean isEmpty()
	{
		return annotation == null;
	}

	public String getText()
	{
		return annotation;
	}

	private final String annotation;
}