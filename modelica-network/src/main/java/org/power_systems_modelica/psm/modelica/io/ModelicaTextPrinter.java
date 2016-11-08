package org.power_systems_modelica.psm.modelica.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.modelica.Annotation;
import org.power_systems_modelica.psm.modelica.AnnotationItem;
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
		this.includePsmAnnotations = false;
	}

	public void setIncludePsmAnnotations(boolean includePsmAnnotations)
	{
		this.includePsmAnnotations = includePsmAnnotations;
	}

	public void print(PrintWriter out) throws IOException
	{
		printWithin(out);
		printSystemModelHeader(out);
		printDeclarations(out);
		printEquations(out);
		if (includePsmAnnotations) printAnnotations(out);
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
		String staticId = ModelicaTricks.staticIdFromDynamicId(m.getId());
		if (staticId == null) staticId = "";
		return staticId;
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
			Annotation a = d.getAnnotation();
			if (a != null && !a.isEmpty())
				out.printf(" annotation (%s)", asText(a));
			out.printf(";%n");
		}
	}

	private final boolean includeAnnotationItem(AnnotationItem a)
	{
		if (!a.isPsmAnnotation()) return true;
		return includePsmAnnotations;
	}

	private String asText(Annotation a)
	{
		String s = a.getItems().stream()
				.filter(a1 -> includeAnnotationItem(a1))
				.map(AnnotationItem::asText)
				.collect(Collectors.joining(","));
		return s;
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
		byKind = (eq1, eq2) -> kindOrdering.compare(
				ModelicaTricks.normalizeKind(getKind(eq1)),
				ModelicaTricks.normalizeKind(getKind(eq2)));
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
			Annotation a = eq.getAnnotation();
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

	private ModelicaDocument	mo;
	private boolean				includePsmAnnotations;
}
