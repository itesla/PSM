package org.power_systems_modelica.psm.modelica;

public class ModelicaConnect extends ModelicaEquation
{
	public ModelicaConnect(String ref1, String ref2)
	{
		this.ref1 = ref1;
		this.ref2 = ref2;
		// No default annotations for system-connect equations
		if (!ModelicaTricks.isSystemConnect(this))
			setAnnotation(new Annotation(DEFAULT_ANNOTATION));
	}

	public String getRef(int side)
	{
		switch (side)
		{
		case 1:
			return ref1;
		case 2:
			return ref2;
		}
		throw new RuntimeException("Bad side in ModelicaConnect getRef, side=" + side);
	}

	public String getRef1()
	{
		return ref1;
	}

	public String getRef2()
	{
		return ref2;
	}

	@Override
	public String getText()
	{
		String[] refs = ModelicaTricks.sortedRefs(this);
		return "connect("
				.concat(refs[0])
				.concat(", ")
				.concat(refs[1])
				.concat(")");
	}

	private final String		ref1;
	private final String		ref2;

	private static final String	DEFAULT_ANNOTATION	= "Line()";
}