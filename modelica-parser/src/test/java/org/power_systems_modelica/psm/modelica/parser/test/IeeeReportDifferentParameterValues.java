package org.power_systems_modelica.psm.modelica.parser.test;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.power_systems_modelica.psm.commons.test.TestUtil.DATA_TMP;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;

public class IeeeReportDifferentParameterValues
{
	// @Test
	public void checkIeeeParams() throws FileNotFoundException, IOException
	{
		List<String> cases = Arrays.asList("14", "30", "57", "118");

		List<List<Param>> pss = cases.stream()
				.map(c -> parseParams(c)).collect(toList());
		List<Map<String, Object>> pmaps = pss.stream()
				.map(ps -> ps.stream().collect(toMap(Param::getKey, Param::getValue)))
				.collect(toList());
		Set<String> allKeys = new TreeSet<>();
		pmaps.forEach(pmap -> allKeys.addAll(pmap.keySet()));

		// Header
		System.out.printf("IEEE_REPORT %-20s\t%-12s\t%-20s\t%-20s", "Equipment", "Phase", "Model",
				"Parameter");
		cases.forEach(c -> System.out.printf("\tIEEE%s", c));
		System.out.printf("\t%s%n", "DIFFERENT_VALUES ?");

		// Values
		allKeys.forEach(k -> {
			List<Object> values = pmaps.stream().map(pmap -> pmap.get(k)).collect(toList());
			List<Object> nonNull = values.stream().filter(o -> o != null).collect(toList());
			boolean differentValues = false;
			if (nonNull.size() > 1)
			{
				Object value = nonNull.get(0);
				for (int j = 1; j < nonNull.size(); j++)
					if (value.equals(nonNull.get(j)) == false)
						differentValues = true;
			}
			String[] kparts = k.split("\\.");
			String phase = kparts[0];
			String model_equipment = kparts[1];
			String parameter = kparts[2];
			String equipment = model_equipment.substring(model_equipment.indexOf("__") + 1);
			String model = model_equipment.substring(0, model_equipment.indexOf("__"));
			System.out.printf("IEEE_REPORT %-20s\t%-12s\t%-20s\t%-20s",
					equipment,
					phase,
					model,
					parameter);
			values.forEach(v -> System.out.printf("\t%20s", v == null ? "" : v.toString()));
			System.out.printf("\t%s%n", differentValues ? "DIFFERENT_VALUES" : "");
		});
	}

	static class Param
	{
		Param(String doc, String did, String name, Object value)
		{
			this.doc = doc;
			this.did = did;
			this.name = name;
			this.value = value;
		}

		String getKey()
		{
			String kdoc;
			if (doc.startsWith("ieee")) kdoc = "simulation";
			else kdoc = "initialization";
			return kdoc + "." + did + "." + name;
		}

		Object getValue()
		{
			return value;
		}

		final String	doc;
		final String	did;
		final String	name;
		final Object	value;
	}

	private void dump(String case_, List<Param> params)
	{
		params.forEach(p -> System.out.printf("IEEE_DUMP %3s %-15s %-35s %-20s %20s%n",
				case_,
				p.doc,
				p.did,
				p.name,
				p.value));
	}

	private List<Param> parseParams(String case_)
	{
		List<Param> params = new ArrayList<>();
		try
		{
			Path mop = getPathModelicaFile(case_);
			ModelicaDocument mo = ModelicaParser.parse(mop);
			addParams(params, mo);
			System.out.println(case_ + " " + mo.getSystemModel().getDeclarations().size());
			Path initsp = getPathModelicaInitFile(case_);
			Files.walkFileTree(initsp, new SimpleFileVisitor<Path>()
			{
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
						throws FileNotFoundException, IOException
				{
					if (file.getFileName().toString().endsWith("single_model.mo"))
					{
						ModelicaDocument moinit = ModelicaParser.parse(file);
						System.out.println("    " + moinit.getSystemModel().getId() + " "
								+ moinit.getSystemModel().getDeclarations().size());
						addParams(params, moinit);
					}
					return FileVisitResult.CONTINUE;
				}
			});
		}
		catch (Exception x)
		{
			System.err.println("ERROR");
			System.err.println(x);
			x.printStackTrace(System.err);
		}
		dump(case_, params);
		return params;
	}

	private void addParams(List<Param> params, ModelicaDocument mo)
	{
		params.addAll(mo.getSystemModel().getDeclarations().stream()
				.filter(d -> d.getArguments() != null)
				.flatMap(d -> d.getArguments().stream()
						.filter(a -> false == IEEE_IGNORE_PARAMS.contains(a.getName()))
						.filter(a -> false == a.getName().startsWith("init_"))
						.map(a -> new Param(
								mo.getSystemModel().getId(),
								d.getId(),
								a.getName(),
								a.getValue())))
				.collect(toList()));
	}

	private Path getPathModelicaFile(String case_)
	{
		return DATA_TMP.resolve("check_params_output_ieee" + case_ + ".mo");
	}

	private Path getPathModelicaInitFile(String case_)
	{
		return DATA_TMP.resolve("check_params_init_ieee" + case_);
	}

	private static final Set<String> IEEE_IGNORE_PARAMS = new HashSet<>(Arrays.asList(
			"ui0",
			"ur0",
			"p0",
			"q0",
			"V_0",
			"angle_0",
			"P_0",
			"Q_0",
			"G",
			"B",
			"R",
			"X"));
}
