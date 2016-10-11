package org.power_systems_modelica.psm.modelica.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.modelica.Annotation;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaTricks;

import com.google.common.collect.Ordering;

public class ModelicaTextPrinter
{
	public ModelicaTextPrinter(ModelicaDocument mo)
	{
		this.mo = mo;
	}

	public void print(PrintWriter out, boolean includeSystemModelAnnotations) throws IOException
	{
		printWithin(out);
		printSystemModelHeader(out);
		printDeclarations(out);
		printEquations(out);
		if (includeSystemModelAnnotations) printAnnotations(out);
		printSystemModelEnd(out);
	}

	private List<ModelicaDeclaration> sortedDeclarations()
	{
		// Sort models by kind (predefined list) and then by model inside each kind
		Ordering<String> kindOrdering = Ordering.explicit(ModelicaTricks.allKinds());
		Comparator<ModelicaDeclaration> byKind, byStaticId;
		byKind = (m1, m2) -> (kindOrdering.compare(
				ModelicaTricks.getKind(m1.getId()),
				ModelicaTricks.getKind(m2.getId())));
		byStaticId = Comparator.comparing(ModelicaTextPrinter::getStaticId);

		List<ModelicaDeclaration> ms0 = mo.getSystemModel().getDeclarations();
		List<ModelicaDeclaration> ms = new ArrayList<>(ms0);
		ms = ms0.stream().sorted(byKind.thenComparing(byStaticId)).collect(Collectors.toList());
		return ms;
	}

	static private String getKind(ModelicaEquation eq)
	{
		if (eq instanceof ModelicaConnect)
		{
			String ref1 = ((ModelicaConnect) eq).getRef1();
			String ref2 = ((ModelicaConnect) eq).getRef2();
			String kind1 = ModelicaTricks.getKind(ref1);
			String kind2 = ModelicaTricks.getKind(ref2);
			String kind = new StringBuilder(kind1.length() + kind2.length() + 1)
					.append(kind1)
					.append("-")
					.append(kind2)
					.toString();
			return kind;
		}
		return null;
	}

	private static String getStaticId(ModelicaDeclaration m)
	{
		String modelId = ModelicaTricks.staticIdFromDynamicId(m.getId());
		if (modelId == null) modelId = "";
		return modelId;
	}

	private static String getKey(ModelicaEquation eq)
	{
		return ModelicaTricks.getKey(eq);
	}

	private void printWithin(PrintWriter out)
	{
		out.printf("within %s;%n", mo.getWithin());
	}

	private void printSystemModelHeader(PrintWriter out)
	{
		out.printf("model %s%n", mo.getSystemModel().getName());
	}

	private void printDeclarations(PrintWriter out)
	{
		for (ModelicaDeclaration d : sortedDeclarations())
		{
			String sparameter = d.isParameter() ? "parameter " : "";
			out.printf("  %s%s %s", sparameter, d.getType(), d.getId());
			if (d.isAssignment() && d.getValue() != null)
			{
				out.printf(" = %s", d.getValue());
			}
			else if (d.getArguments() != null)
			{
				out.printf(" (%n");
				Iterator<ModelicaArgument> k = d.getArguments().iterator();
				if (k.hasNext())
				{
					printArgument(out, k.next());
					while (k.hasNext())
					{
						out.printf(",%n");
						printArgument(out, k.next());
					}
				}
				out.printf("%n    )");
			}
			Annotation a = annotation(d);
			if (a != null && !a.isEmpty())
				out.printf(" annotation (%s)", asText(a));
			out.printf(";%n");
		}
	}

	private Annotation annotation(ModelicaDeclaration d)
	{
		String dtype = d.getType();
		System.err.println(dtype);

		// For assignments, return the annotation as it is (maybe empty)
		if (d.isAssignment()) return d.getAnnotation();
		// For the rest of declarations, provide a default annotation if given is empty
		if (d.getAnnotation() == null || d.getAnnotation().isEmpty())
			return DECLARATION_DEFAULT_ANNOTATION;
		return d.getAnnotation();
	}

	private Annotation annotation(ModelicaEquation eq)
	{
		// If the annotation exists and is not empty, return it
		if (eq.getAnnotation() != null && !eq.getAnnotation().isEmpty()) return eq.getAnnotation();
		// Provide default annotations for some types of equations
		if (eq instanceof ModelicaConnect)
		{
			// No annotations for system connect equations
			if (ModelicaTricks.isSystemConnect((ModelicaConnect) eq)) return null;
			else return CONNECT_DEFAULT_ANNOTATION;
		}
		return null;
	}

	private String asText(Annotation a)
	{
		// return a.getItems().stream().collect(Collectors.joining(","));
		if (a == null) return "";
		String text = a.getText();
		if (text == null) return "";
		return text;
	}

	private void printArgument(PrintWriter out, ModelicaArgument a)
	{
		out.printf("    %s = %s", a.getName(), a.getValue());
	}

	private List<ModelicaEquation> sortedEquations()
	{
		// Will sort ModelicaEquations first by type,
		// Then for connect equations by kind (according to an explicit ordering of possible kinds)
		// Then by staticId,
		// Then by ordering defined by the original list

		List<ModelicaEquation> eqs0 = mo.getSystemModel().getEquations();
		List<ModelicaEquation> eqs = new ArrayList<>(eqs0);

		Comparator<ModelicaEquation> byType, byKind, byKey;
		Ordering<ModelicaEquation> byOriginal = Ordering.explicit(eqs0);
		Ordering<String> kindOrdering = Ordering.explicit(ModelicaTricks.allKindPairs());
		byType = (eq1, eq2) -> -eq1.getClass().getName().compareTo(eq2.getClass().getName());
		byKind = (eq1, eq2) -> {
			String kind1 = getKind(eq1);
			String kind2 = getKind(eq2);
			System.err.printf("%-10s %-10s%n    %s%n    %s%n", kind1, kind2, eq1.getText(),
					eq2.getText());
			return kindOrdering.compare(getKind(eq1), getKind(eq2));
		};
		byKey = Comparator.comparing(ModelicaTextPrinter::getKey);

		eqs = eqs0.stream()
				.sorted(byType.thenComparing(byKind).thenComparing(byKey).thenComparing(byOriginal))
				.collect(Collectors.toList());
		return eqs;
	}

	private void printEquations(PrintWriter out)
	{
		out.printf("equation%n");
		for (ModelicaEquation eq : sortedEquations())
		{
			out.printf("  %s", eq.getText());
			Annotation a = annotation(eq);
			if (a != null && !a.isEmpty())
				out.printf(" annotation (%s)", asText(a));
			out.printf(";%n");
		}
	}

	private void printAnnotations(PrintWriter out)
	{
		for (Annotation a : mo.getSystemModel().getAnnotations())
			if (!a.isEmpty())
				out.printf("  annotation (%s);%n", asText(a));
	}

	private void printSystemModelEnd(PrintWriter out)
	{
		out.printf("end %s;%n", mo.getSystemModel().getName());
	}

	private ModelicaDocument		mo;

	private static final Annotation	DECLARATION_DEFAULT_ANNOTATION	= new Annotation(
			"Placement(transformation())");
	private static final Annotation	CONNECT_DEFAULT_ANNOTATION		= new Annotation(
			"Line()");
}
