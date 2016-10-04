package org.power_systems_modelica.psm.modelica;

public interface Annotable
{

	public Annotation getAnnotation();

	public void resetAnnotation();

	public void addAnnotation(String annotation);

}
