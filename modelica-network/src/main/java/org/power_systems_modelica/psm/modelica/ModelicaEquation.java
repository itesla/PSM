package org.power_systems_modelica.psm.modelica;

public class ModelicaEquation
{
	public ModelicaEquation()
	{
		annotation = DEFAULT_ANNOTATION;
	}

	public String getAnnotation()
	{
		return annotation;
	}

	public void setAnnotation(String annotation)
	{
		this.annotation = annotation;
	}

	private String				annotation;

	private static final String	DEFAULT_ANNOTATION	= "Line()";
}
