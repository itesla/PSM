package org.power_systems_modelica.psm.modelica;

public class ModelicaEquation implements Annotable
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

	private final String		text;

	private final Annotation	annotation			= new Annotation(DEFAULT_ANNOTATION);
	private static final String	DEFAULT_ANNOTATION	= "Line()";
}
