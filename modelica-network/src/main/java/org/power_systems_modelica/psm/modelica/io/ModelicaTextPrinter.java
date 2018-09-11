package org.power_systems_modelica.psm.modelica.io;

/*
 * #%L
 * Modelica network model
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

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
import org.power_systems_modelica.psm.modelica.BaseModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaTricks;

import com.google.common.collect.Ordering;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
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
		// System level annotation only if we are including psm annotations
		printSystemAnnotation();
		printSystemModelEnd();
	}

	private List<BaseModelicaDeclaration> sortedDeclarations()
	{
		// Sort models by kind (predefined list) and then by model inside each kind
		Ordering<String> kindOrdering = Ordering.explicit(ModelicaTricks.allKinds());
		Comparator<BaseModelicaDeclaration> byKind, byStaticId;
		byKind = (m1, m2) -> {
			if (!ModelicaTricks.allEquationKinds().contains(ModelicaTricks.getKind(m1.getId()))
					|| !ModelicaTricks.allEquationKinds()
							.contains(ModelicaTricks.getKind(m2.getId())))
			{
				return 1;
			}
			else
			{
				return kindOrdering.compare(
						ModelicaTricks.getKind(m1.getId()),
						ModelicaTricks.getKind(m2.getId()));
			}
		};
		byStaticId = Comparator.comparing(ModelicaTextPrinter::getStaticId);

		List<BaseModelicaDeclaration> ms0 = mo.getSystemModel().getDeclarations();
		List<BaseModelicaDeclaration> ms = new ArrayList<>(ms0);
		ms = ms0.stream().sorted(byKind.thenComparing(byStaticId)).collect(Collectors.toList());
		return ms;
	}

	static private String getKind(ModelicaEquation eq)
	{
		return ModelicaTricks.getKind(eq);
	}

	private static String getStaticId(BaseModelicaDeclaration m)
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
		String indent = INDENT;
		String indent1 = String.format("%s%s", indent, INDENT);
		for (BaseModelicaDeclaration d : sortedDeclarations())
		{
			String sparameter = d.isParameter() ? "parameter " : "";
			pw.printf("%s%s%s %s", indent, sparameter, d.getType(), d.getId());
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
					printArgument(k.next(), indent);
					while (k.hasNext())
					{
						pw.printf(",%n");
						printArgument(k.next(), indent);
					}
				}
				pw.printf("%n%s)", indent1);
			}
			printAnnotation(d.getAnnotation(), " ", indent1);
			pw.printf(";%n");
		}
	}

	private boolean printAnnotation(Annotation a, String prefix, String indent)
	{
		if (a != null && !a.isEmpty())
		{
			String sa = asText(a, indent);
			if (!sa.isEmpty())
			{
				pw.printf("%sannotation (%s)", prefix, sa);
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

	private String asText(Annotation a, String indent)
	{
		String joiner = a.getItems().size() <= 2 ? "," : String.format(",\n%s%s", indent, INDENT);
		String s = a.getItems().stream()
				.filter(a1 -> includeAnnotationItem(a1))
				.map(AnnotationItem::asText)
				.sorted()
				.collect(Collectors.joining(joiner));
		return s;
	}

	private void printArgument(ModelicaArgument a, String indent)
	{
		pw.printf("%s%s%s = %s", indent, INDENT, a.getName(), a.getValue());
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
		byKind = (eq1, eq2) -> {
			if (!ModelicaTricks.allEquationKinds().contains(getKind(eq1))
					|| !ModelicaTricks.allEquationKinds().contains(getKind(eq2)))
			{
				return 1;
			}
			else
			{
				return kindOrdering.compare(
						ModelicaTricks.normalizeKind(getKind(eq1)),
						ModelicaTricks.normalizeKind(getKind(eq2)));
			}
		};
		byKey = Comparator.comparing(ModelicaTextPrinter::getKey);

		eqs = eqs0.stream()
				.sorted(byType.thenComparing(byKind).thenComparing(byKey).thenComparing(byOriginal))
				.collect(Collectors.toList());
		return eqs;
	}

	private void printEquations()
	{
		pw.printf("equation%n");
		String indent = INDENT;
		for (ModelicaEquation eq : sortedEquations())
		{
			// pw.printf(" // kind = %s%n", ModelicaTricks.normalizeKind(getKind(eq)));
			pw.printf("%s%s", indent, eq.getText());
			printAnnotation(eq.getAnnotation(), " ", indent);
			pw.printf(";%n");
		}
	}

	private void printSystemAnnotation()
	{
		if (printAnnotation(mo.getSystemModel().getAnnotation(), INDENT, INDENT))
			pw.printf(";%n");
	}

	private void printSystemModelEnd()
	{
		pw.printf("end %s;%n", mo.getSystemModel().getId());
	}

	private final String			INDENT	= "  ";
	private final ModelicaDocument	mo;
	private final boolean			includePsmAnnotations;
	private final PrintWriter		pw;
}
