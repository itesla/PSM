package org.power_systems_modelica.psm.modelica;

public class ModelicaConnect extends ModelicaEquation
{
	public ModelicaConnect(String ref1, String ref2)
	{
		this.ref1 = ref1;
		this.ref2 = ref2;
	}

	public String getRef1()
	{
		return ref1;
	}

	public String getRef2()
	{
		return ref2;
	}

	public String toString()
	{
		return new StringBuilder()
				.append("connect(")
				.append("ref1=")
				.append(ref1)
				.append(", ref2=")
				.append(ref2)
				.append(")")
				.toString();
	}

	private final String	ref1;
	private final String	ref2;
}