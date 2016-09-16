package org.power_systems_modelica.psm.modelica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModelicaTricks
{
	public static String getKind(String name)
	{
		if (name.equals("SNREF")) return "system";

		int p = name.indexOf("_");
		if (p < 0) return "other";

		String kind = name.substring(0, p);
		if (kind == null) kind = "other";
		if (!KINDS_SET.contains(kind)) kind = "other";
		return kind;
	}

	public static String getModel(String name)
	{
		// We are assuming dynamic component identifiers are built as: <type>_<static_id>
		// and also that all static identifiers begin with an underscore
		int p = name.indexOf("__");
		if (p >= 0) return name.substring(p + 1);
		return null;
	}

	public static String getKey(ModelicaEquation eq)
	{
		// In the case of a connect block we assign this equation to the id of the branch or the first id
		if (eq instanceof ModelicaConnect)
		{
			String[] namevar1 = ModelicaUtil.ref2idvar(((ModelicaConnect) eq).getRef1());
			String[] namevar2 = ModelicaUtil.ref2idvar(((ModelicaConnect) eq).getRef2());

			String name1 = namevar1[0];
			String name2 = namevar2[0];
			String id1 = name1.substring(name1.indexOf("__"));
			if (id1 == null) id1 = "";
			String id2 = name2.substring(name2.indexOf("__"));
			if (id2 == null) id2 = "";

			String id = id1;
			// If the connect is a bus-branch, use the id of the branch
			if (name2.startsWith("line_") || name2.startsWith("trafo_")) id = id2;
			// First the connection busFrom-branch (pin p)
			String var2 = namevar2[1];
			if (var2.equals("n")) id += "z";

			return id;
		}
		return null;
	}

	public static String[] sortedRefs(ModelicaConnect eqc)
	{
		String[] refs = { eqc.getRef1(), eqc.getRef2() };
		if (eqc.getRef2().endsWith(".n"))
		{
			String[] reversed = { eqc.getRef2(), eqc.getRef1() };
			refs = reversed;
		}
		return refs;
	}

	public static ModelicaConnector preferredConnector(
			ModelicaModel m,
			ModelicaModel other,
			ModelicaConnector[] conns)
	{
		// This is only to assign the same pins that old conversion software
		// that is, assign pin "p" of branch to connection with bus from
		// and assign pin "n" of branch to connection with bus to

		assert (conns.length == 2);
		ModelicaModel bus = m;
		ModelicaModel branch = other;
		String otherName = other.getDeclarations().get(0).getId();
		if (otherName.startsWith("bus_"))
		{
			bus = other;
			branch = m;
		}

		String branchName = branch.getDeclarations().get(0).getId();
		String idBus = bus.getStaticId().replace("_TN", "");
		int pBus = branchName.indexOf(idBus);
		int pBusFrom = branchName.indexOf("__") + 1;
		boolean isBusFrom = pBus == pBusFrom;

		return isBusFrom ? conns[0] : conns[1];
	}

	public static String getStaticTypeFromDynamicType(String dtype)
	{
		switch (dtype)
		{
		case "iPSL.Electrical.Buses.Bus":
			return "Bus";
		case "iPSL.Electrical.Branches.PwLine_2":
			return "Line";
		case "iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer":
			return "Transformer";
		case "iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence":
			return "Load";
		case "iPSL.Electrical.Banks.PwCapacitorBank":
			return "Shunt";
		case "iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S":
			return "Generator";
		}
		return null;
	}

	public static String legacyType(String type)
	{
		switch (type)
		{
		case "Bus":
			return "bus";
		case "Line":
			return "line";
		case "Transformer":
			return "trafo";
		case "Load":
			return "load";
		case "Shunt":
			return "cap";
		default:
			return "";
		}
	}

	public static List<String> allKinds()
	{
		return ALL_KINDS;
	}

	public static List<String> allKindPairs()
	{
		return ALL_KIND_PAIRS;
	}

	private static final List<String>	KINDS			= Arrays.asList(
			"system",
			"bus",
			"Bus",
			"load",
			"Load",
			"trafo",
			"Transformer",
			"line",
			"Line",
			"cap",
			"Shunt",
			"gen",
			"Generator",
			"reg");
	private static final List<String>	KIND_PAIRS		= Arrays.asList(
			"reg-gen",
			"reg-reg",
			"bus-line",
			"Bus-Line",
			"bus-load",
			"Bus-Load",
			"bus-cap",
			"Bus-Shunt",
			"bus-gen",
			"Bus-gen",
			"bus-trafo",
			"Bus-Transformer");

	private static final Set<String>	KINDS_SET		= new HashSet<>(KINDS);
	private static final List<String>	ALL_KINDS		= new ArrayList<>(KINDS);
	private static final List<String>	ALL_KIND_PAIRS	= new ArrayList<>(KIND_PAIRS);
	static
	{
		ALL_KINDS.add("other");
		ALL_KIND_PAIRS.add("other-other");
		ALL_KIND_PAIRS.add("other-gen");
	}
}
