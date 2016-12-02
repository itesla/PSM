package org.power_systems_modelica.psm.modelica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
		if (p >= 0)
		{
			String id0 = name.substring(p + 1);
			if (mappingDynamicId2StaticId.containsKey(id0))
				return mappingDynamicId2StaticId.get(id0);
			else return id0;
		}
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
			String id1 = staticIdFromDynamicId(name1);
			if (id1 == null) id1 = name1;
			String id2 = staticIdFromDynamicId(name2);
			if (id2 == null) id2 = name2;

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
		return "other";
	}

	public static String getKind(ModelicaEquation eq)
	{
		if (eq instanceof ModelicaConnect)
		{
			String ref1 = ((ModelicaConnect) eq).getRef1();
			String ref2 = ((ModelicaConnect) eq).getRef2();
			String kind1 = getKind(ref1);
			String kind2 = getKind(ref2);
			String kind = new StringBuilder(kind1.length() + kind2.length() + 1)
					.append(kind1)
					.append("-")
					.append(kind2)
					.toString();
			return kind;
		}
		return "other";
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
		if (dtype.startsWith("iPSL.Electrical.Buses.")) return "Bus";
		else if (dtype.startsWith("iPSL.Electrical.Branches"))
		{
			if (dtype.contains("Line")) return "Line";
			else if (dtype.contains("Transformer")) return "Transformer";
		}
		else if (dtype.startsWith("iPSL.Electrical.Loads.")) return "Load";
		else if (dtype.startsWith("iPSL.Electrical.Banks.")) return "Shunt";
		else if (dtype.startsWith("iPSL.Electrical.Machines")) return "Generator";
		return null;
	}

	public static boolean isSystemConnect(ModelicaConnect eq)
	{
		return getKind(eq.getRef1()).equals("system") || getKind(eq.getRef2()).equals("system");
	}

	public static List<String> allKinds()
	{
		return ALL_KINDS;
	}

	public static List<String> allEquationKinds()
	{
		return ALL_EQUATION_KINDS;
	}

	public static String normalizeKind(String kind)
	{
		if (kind.equals("line-bus")) return "bus-line";
		else if (kind.equals("trafo-bus")) return "bus-trafo";
		return kind;
	}

	private static final List<String>	KINDS				= Arrays.asList(
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
	private static final List<String>	KIND_PAIRS			= Arrays.asList(
			"gen-system",
			"system-gen",
			"reg-gen",
			"reg-reg",
			// bus-other may correspond to a bus-fault for example
			"bus-other",
			"bus-line",
			"Bus-Line",
			// "line-bus" must be equivalent to bus-line, we have normalized
			"bus-load",
			"Bus-Load",
			"bus-cap",
			"Bus-Shunt",
			"bus-gen",
			"Bus-gen",
			"bus-trafo",
			// "trafo-bus" is equivalent to bus-trafo, we have normalized
			"Bus-Transformer");

	private static final Set<String>	KINDS_SET			= new HashSet<>(KINDS);
	private static final List<String>	ALL_KINDS			= new ArrayList<>(KINDS);
	private static final List<String>	ALL_EQUATION_KINDS	= new ArrayList<>(KIND_PAIRS);
	static
	{
		ALL_KINDS.add("other");

		ALL_EQUATION_KINDS.add("system");
		ALL_EQUATION_KINDS.add("other");
		ALL_EQUATION_KINDS.add("other-other");
		ALL_EQUATION_KINDS.add("other-gen");
	}
	private static final Map<String, String> mappingDynamicId2StaticId = new HashMap<>();
	static
	{
		mappingDynamicId2StaticId.put("_N__E__16_TN", "_N._E__16_TN");
		mappingDynamicId2StaticId.put("_N__E__16_EC", "_N._E__16_EC");
		mappingDynamicId2StaticId.put("_N_NE__45_TN", "_N.NE__45_TN");
		mappingDynamicId2StaticId.put("_N_NE__45_EC", "_N.NE__45_EC");
		mappingDynamicId2StaticId.put("_S_KE__43_TN", "_S.KE__43_TN");
		mappingDynamicId2StaticId.put("_S_KE__43_EC", "_S.KE__43_EC");
		mappingDynamicId2StaticId.put("_S_TI__41_TN", "_S.TI__41_TN");
		mappingDynamicId2StaticId.put("_S_TI__41_EC", "_S.TI__41_EC");
		mappingDynamicId2StaticId.put("_W_KA__61_TN", "_W.KA__61_TN");
		mappingDynamicId2StaticId.put("_W_LA__46_TN", "_W.LA__46_TN");
		mappingDynamicId2StaticId.put("_W_LA__46_EC", "_W.LA__46_EC");

		mappingDynamicId2StaticId.put("_N_NE__45_PHIL__49_1_AC", "_N.NE__45_PHIL__49_1_AC");
		mappingDynamicId2StaticId.put("_N_NE__45_W_LA__46_1_AC", "_N.NE__45_W.LA__46_1_AC");
		mappingDynamicId2StaticId.put("_N__E__16_SORE__17_1_AC", "_N._E__16_SORE__17_1_AC");
		mappingDynamicId2StaticId.put("_ROCK__34_S_KE__43_1_AC", "_ROCK__34_S.KE__43_1_AC");
		mappingDynamicId2StaticId.put("_S_KE__43_WMVE__44_1_AC", "_S.KE__43_WMVE__44_1_AC");
		mappingDynamicId2StaticId.put("_S_TI__41_HOWA__42_1_AC", "_S.TI__41_HOWA__42_1_AC");
		mappingDynamicId2StaticId.put("_W_KA__61_NATR__62_1_AC", "_W.KA__61_NATR__62_1_AC");
		mappingDynamicId2StaticId.put("_W_LA__46_CROO__47_1_AC", "_W.LA__46_CROO__47_1_AC");
		mappingDynamicId2StaticId.put("_W_LA__46_ZANE__48_1_AC", "_W.LA__46_ZANE__48_1_AC");

		mappingDynamicId2StaticId.put("_W_LA__46_SC", "_W.LA__46_SC");
	}
}
