package org.power_systems_modelica.psm.modelica;

public class ModelicaEquation implements Annotable
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

	private final String	text;

	private Annotation		annotation;
}
