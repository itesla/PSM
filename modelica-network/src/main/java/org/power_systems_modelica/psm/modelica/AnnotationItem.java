package org.power_systems_modelica.psm.modelica;

import java.util.Objects;

public class AnnotationItem implements Comparable<AnnotationItem>
{
	public AnnotationItem(String text)
	{
		Objects.requireNonNull(text);
		this.text = text;
	}

	public String asText()
	{
		return text;
	}

	public boolean isPsmAnnotation()
	{
		return text.startsWith("PSM");
	}

	private final String text;

	@Override
	public int compareTo(AnnotationItem other)
	{
		return text.compareTo(other.text);
	}
}
