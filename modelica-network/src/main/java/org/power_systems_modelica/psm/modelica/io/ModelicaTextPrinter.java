package org.power_systems_modelica.psm.modelica.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.modelica.Annotation;
import org.power_systems_modelica.psm.modelica.AnnotationItem;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaTricks;

import com.google.common.collect.Ordering;

public class ModelicaTextPrinter
{
	public static void print(ModelicaDocument mo, Path p, boolean includePsmAnnotations)
			throws IOException
	{
		try (PrintWriter w = new PrintWriter(p.toFile()))
		{
			print(mo, w, includePsmAnnotations);
		}
	}

	public static void print(ModelicaDocument mo, PrintWriter pw, boolean includePsmAnnotations)
			throws IOException
	{
		new ModelicaTextPrinter(mo, pw, includePsmAnnotations).print();
	}

	private ModelicaTextPrinter(ModelicaDocument mo, PrintWriter pw, boolean includePsmAnnotations)
	{
		this.mo = mo;
		this.includePsmAnnotations = includePsmAnnotations;
		this.pw = pw;
	}

	public void print() throws IOException
	{
		printWithin();
		printSystemModelHeader();
		printDeclarations();
		printEquations();
		// System level annotations only if we are including psm annotations
		printSystemAnnotations();
		printSystemModelEnd();
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
		return ModelicaTricks.getKind(eq);
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

	private void printWithin()
	{
		pw.printf("within %s;%n", mo.getWithin());
	}

	private void printSystemModelHeader()
	{
		pw.printf("model %s%n", mo.getSystemModel().getId());
	}

	private void printDeclarations()
	{
		for (ModelicaDeclaration d : sortedDeclarations())
		{
			String sparameter = d.isParameter() ? "parameter " : "";
			pw.printf("  %s%s %s", sparameter, d.getType(), d.getId());
			if (d.isAssignment() && d.getValue() != null)
			{
				pw.printf(" = %s", d.getValue());
			}
			else if (d.getArguments() != null)
			{
				pw.printf(" (%n");
				Iterator<ModelicaArgument> k = d.getArguments().iterator();
				if (k.hasNext())
				{
					printArgument(k.next());
					while (k.hasNext())
					{
						pw.printf(",%n");
						printArgument(k.next());
					}
				}
				pw.printf("%n    )");
			}
			printAnnotation(d.getAnnotation(), " ");
			pw.printf(";%n");
		}
	}

	private boolean printAnnotation(Annotation a, String indent)
	{
		if (a != null && !a.isEmpty())
		{
			String sa = asText(a);
			if (!sa.isEmpty())
			{
				pw.printf("%sannotation (%s)", indent, sa);
				return true;
			}
		}
		return false;
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

	private void printArgument(ModelicaArgument a)
	{
		pw.printf("    %s = %s", a.getName(), a.getValue());
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
		Ordering<String> kindOrdering = Ordering.explicit(ModelicaTricks.allEquationKinds());
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

	private void printEquations()
	{
		pw.printf("equation%n");
		for (ModelicaEquation eq : sortedEquations())
		{
			// pw.printf(" // kind = %s%n", ModelicaTricks.normalizeKind(getKind(eq)));
			pw.printf("  %s", eq.getText());
			printAnnotation(eq.getAnnotation(), " ");
			pw.printf(";%n");
		}
	}

	private void printSystemAnnotations()
	{
		for (Annotation a : mo.getSystemModel().getAnnotations())
		{
			if (printAnnotation(a, "  "))
				pw.printf(";%n");
		}
	}

	private void printSystemModelEnd()
	{
		pw.printf("end %s;%n", mo.getSystemModel().getId());
	}

	private final ModelicaDocument	mo;
	private final boolean			includePsmAnnotations;
	private final PrintWriter		pw;
}
