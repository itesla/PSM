package org.power_systems_modelica.psm.modelica;

public class ModelicaEquation
{
	public ModelicaEquation()
	{
		text = null;
	}

	public ModelicaEquation(String text)
	{
		this.text = text;
	}

	public String getText()
	{
		return text;
	}

	public String getAnnotation()
	{
		return annotation;
	}

	public void setAnnotation(String annotation)
	{
		this.annotation = annotation;
	}

	private final String		text;
	private String				annotation			= DEFAULT_ANNOTATION;

	private static final String	DEFAULT_ANNOTATION	= "Line()";
}
