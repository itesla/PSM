package org.power_systems_modelica.psm.modelica;

public class ModelicaEquation
{
	public ModelicaEquation()
	{
		text = null;
		annotation = null;
	}

	public ModelicaEquation(String text)
	{
		this.text = text;
		this.annotation = null;
	}

	public ModelicaEquation(String text, Annotation annotation)
	{
		this.text = text;
		this.annotation = annotation;
	}

	public String getText()
	{
		return text;
	}

	public Annotation getAnnotation()
	{
		return annotation;
	}

	private final String		text;
	private final Annotation	annotation;
}
