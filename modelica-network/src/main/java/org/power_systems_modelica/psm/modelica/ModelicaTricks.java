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
		if (name.equals("omegaRef")) return "system";

		int p = name.indexOf("_");
		if (p < 0) return "other";

		String kind = name.substring(0, p);
		if (kind == null) kind = "other";
		if (!KINDS_SET.contains(kind)) kind = "other";
		return kind;
	}

	public static String staticIdFromDynamicId(String name)
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

			int p;
			String name1 = namevar1[0];
			String name2 = namevar2[0];
			String id1 = name1;
			p = name1.indexOf("__");
			if (p >= 0) id1 = name1.substring(p);
			if (id1 == null) id1 = "";
			String id2 = name2;
			p = name2.indexOf("__");
			if (p >= 0) id2 = name2.substring(p);
			if (id2 == null) id2 = "";

			String key = id1.concat(":").concat(id2);

			// If the connect is for a branch, use only the id of the branch
			// And put first the connection (bus1 - branch), that corresponds to pin "p"
			boolean isBranch1 = name1.startsWith("line_") || name1.startsWith("trafo_");
			boolean isBranch2 = name2.startsWith("line_") || name2.startsWith("trafo_");
			String branchPin = "";
			if (isBranch1)
			{
				key = id1;
				if (namevar1.length > 1) branchPin = namevar1[1];
			}
			else if (isBranch2)
			{
				key = id2;
				if (namevar2.length > 1) branchPin = namevar2[1];
			}
			if (branchPin.equals("n")) key += "z";

			return key;
		}
		return null;
	}

	public static String[] sortedRefs(ModelicaConnect eqc)
	{
		String[] refs = { eqc.getRef1(), eqc.getRef2() };

		boolean reverse = false;
		if (eqc.getRef2().endsWith(".n")) reverse = true;
		if (eqc.getRef1().equals("omegaRef")) reverse = true;
		if (reverse)
		{
			String[] reversed = { eqc.getRef2(), eqc.getRef1() };
			refs = reversed;
		}

		return refs;
	}

	public static boolean isBusAtSide(
			ModelicaModel branch,
			ModelicaModel bus,
			int side)
	{
		// This is only to assign the same pins that old conversion software
		// that is, assign pin "p" of branch to connection with bus from
		// and assign pin "n" of branch to connection with bus to

		String branchName = branch.getDeclarations().get(0).getId();
		String idBus = bus.getStaticId().replace("_TN", "");
		int pBus = branchName.indexOf(idBus);
		int pBus1 = branchName.indexOf("__") + 1;
		boolean isBus1 = pBus == pBus1;

		switch (side)
		{
		case 1:
			return isBus1;
		case 2:
			return !isBus1;
		default:
			return false;
		}
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

	public static boolean isSystemConnect(ModelicaConnect eq)
	{
		return getKind(eq.getRef1()).equals("system") || getKind(eq.getRef2()).equals("system");
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
			"gen-system",
			"system-gen",
			"reg-gen",
			"reg-reg",
			"bus-line",
			"Bus-Line",
			"line-bus",
			"bus-load",
			"Bus-Load",
			"bus-cap",
			"Bus-Shunt",
			"bus-gen",
			"Bus-gen",
			"bus-trafo",
			"trafo-bus",
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
