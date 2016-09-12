package org.power_systems_modelica.psm.modelica.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaModelInstantiation;
import org.power_systems_modelica.psm.modelica.ModelicaParameter;
import org.power_systems_modelica.psm.modelica.ModelicaTricks;

import com.google.common.collect.Ordering;

public class ModelicaTextPrinter
{
	public ModelicaTextPrinter(ModelicaDocument mo)
	{
		this.mo = mo;
	}

	public void print(PrintWriter out) throws IOException
	{
		printWithin(out);
		printSystemModelHeader(out);
		printParameters(out);
		printModelInstantiations(out);
		printEquations(out);
		printSystemModelEnd(out);
	}

	private List<ModelicaModelInstantiation> sortedModels()
	{
		// Sort models by kind (predefined list) and then by model inside each kind
		Ordering<String> kindOrdering = Ordering.explicit(
				"bus",
				"load",
				"trafo",
				"line",
				"cap",
				"gen",
				"reg",
				"other");
		Comparator<ModelicaModelInstantiation> byKind, byId;
		byKind = (m1, m2) -> (kindOrdering.compare(getKind(m1), getKind(m2)));
		byId = (m1, m2) -> getModel(m1).compareTo(getModel(m2));

		List<ModelicaModelInstantiation> ms0 = mo.getSystemModel().getModelInstantiations();
		List<ModelicaModelInstantiation> ms = new ArrayList<>(ms0);
		ms = ms0.stream().sorted(byKind.thenComparing(byId)).collect(Collectors.toList());
		return ms;
	}

	private static String getKind(ModelicaModelInstantiation m)
	{
		return ModelicaTricks.getKind(m.getName());
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

	private static String getModel(ModelicaModelInstantiation m)
	{
		String modelId = ModelicaTricks.getModel(m.getName());
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

	private void printParameters(PrintWriter out)
	{
		for (ModelicaParameter p : mo.getSystemModel().getParameters())
		{
			out.printf("  parameter %s %s = %s;%n", p.getType(), p.getName(), p.getValue());
		}
	}

	private void printModelInstantiations(PrintWriter out)
	{
		for (ModelicaModelInstantiation m : sortedModels())
		{
			out.printf("  %s %s", m.getType(), m.getName());
			if (m.getArguments() != null)
			{
				out.printf(" (%n");
				Iterator<ModelicaArgument> k = m.getArguments().iterator();
				if (k.hasNext())
				{
					printInstantiationArgument(out, k.next());
					while (k.hasNext())
					{
						out.printf(",%n");
						printInstantiationArgument(out, k.next());
					}
				}
				out.printf("%n    )");
			}
			out.printf(" annotation (%s);%n", m.getAnnotation());
		}
	}

	private void printInstantiationArgument(PrintWriter out, ModelicaArgument a)
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
		Ordering<String> kindOrdering = Ordering.explicit(
				"reg-gen",
				"reg-reg",
				"bus-line",
				"bus-load",
				"bus-cap",
				"bus-gen",
				"bus-trafo");
		byType = (eq1, eq2) -> eq1.getClass().getName().compareTo(eq2.getClass().getName());
		byKind = (eq1, eq2) -> kindOrdering.compare(getKind(eq1), getKind(eq2));
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
			// TODO instanceof. Think how to enforce new ModelicaEquations provide all required methods without reviewing instanceof everywhere
			if (eq instanceof ModelicaConnect)
			{
				ModelicaConnect eqc = (ModelicaConnect) eq;
				String[] refs = ModelicaTricks.sortedRefs(eqc);
				out.printf("  connect(%s, %s) ", refs[0], refs[1]);
			}
			out.printf("annotation (%s);%n", eq.getAnnotation());
		}
	}

	private void printSystemModelEnd(PrintWriter out)
	{
		out.printf("end %s;%n", mo.getSystemModel().getName());
	}

	private ModelicaDocument mo;
}
