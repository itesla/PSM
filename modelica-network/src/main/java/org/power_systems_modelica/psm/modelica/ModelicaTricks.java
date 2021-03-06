package org.power_systems_modelica.psm.modelica;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelicaTricks
{
	public static String getKind(String name)
	{
		if (name.equals("SysData")) return "system";
		if (name.startsWith("omegaRef")) return "system";

		int p = name.indexOf("_");
		if (p < 0) return "other";

		String kind = name.substring(0, p);
		if (kind == null) kind = "other";
		if (!KINDS_SET.contains(kind)) kind = "other";
		return kind;
	}

	public static String staticIdFromDynamicId(String name)
	{
		if (name == null) return null;

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
			if (name1 == null) name1 = "";
			if (name2 == null) name2 = "";
			String id1 = staticIdFromDynamicId(name1);
			String id2 = staticIdFromDynamicId(name2);
			if (id1 == null) id1 = name1;
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

	public static boolean isInfiniteBus(ModelicaModel m)
	{
		return m.getDeclarations().stream().anyMatch(d -> d.getType().contains("InfiniteBus"));
	}
	
	public static boolean isSystemBase(ModelicaModel m)
	{
		return m.getDeclarations().stream().anyMatch(d -> d.getType().endsWith("SystemBase"));
	}
	
	public static boolean isSystemBaseSB(BaseModelicaDeclaration md)
	{
		return md.getArguments().stream().anyMatch(a -> a.getName().equals("S_b"));
	}
	
	public static BaseModelicaDeclaration getSystemBase(ModelicaModel m) {
		return m.getDeclarations().stream()
				.filter(d -> d.getType().endsWith("SystemBase"))
				.findFirst().get();
	}
	
	public static ModelicaArgument getSystemBaseSB(BaseModelicaDeclaration md) {
		return md.getArguments().stream()
				.filter(e -> e.getName().equals("S_b"))
				.findFirst().get();
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

	private static final List<String>			KINDS						= Arrays.asList(
			"system",
			"bus",
			"busInf",
			"Bus",
			"load",
			"Load",
			"trafo",
			"Transformer",
			"line",
			"Line",
			"cap",
			"Shunt",
			"fixinj",
			"gen",
			"Generator",
			"reg",
			"other");

	private static final List<String>			ALL_EQUATION_KINDS			= Arrays.asList(
			"system",
			"gen-system",
			"system-gen",
			"load-system",
			"system-load",
			"reg-gen",
			"reg-reg",
			// bus-bus correspond to switches
			"bus-bus",
			"bus-busInf",
			"bus-line",
			"Bus-Line",
			// "line-bus" must be equivalent to bus-line, we have normalized
			"bus-load",
			"Bus-Load",
			"bus-cap",
			"Bus-Shunt",
			"bus-gen",
			"Bus-gen",
			"bus-fixinj",
			"Bus-fixinj",
			"busInf-fixinj",
			"bus-trafo",
			// "trafo-bus" is equivalent to bus-trafo, we have normalized
			"Bus-Transformer",
			// bus-other may correspond to a bus-fault for example
			"bus-other",
			"other",
			"other-other",
			"other-gen",
			"gen-other",
			"reg-other",
			"load-other");

	private static final Set<String>			KINDS_SET					= new HashSet<>(KINDS);
	private static final List<String>			ALL_KINDS					= new ArrayList<>(
			KINDS);

	private static final Map<String, String>	mappingDynamicId2StaticId	= new HashMap<>();
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

		mappingDynamicId2StaticId.put("_FP_AND11_TN", "_FP.AND11_TN");
		mappingDynamicId2StaticId.put("_FP_ANC12_EC", "_FP.ANC12_EC");
		mappingDynamicId2StaticId.put("_FS_BIS12_TN", "_FS.BIS12_TN");
		mappingDynamicId2StaticId.put("_FS_BIS11_TN", "_FS.BIS11_TN");
		mappingDynamicId2StaticId.put("_FS_BIC11_EC", "_FS.BIC11_EC");
		mappingDynamicId2StaticId.put("_FSSV_O12_TN", "_FSSV.O12_TN");
		mappingDynamicId2StaticId.put("_FSSV_O11_TN", "_FSSV.O11_TN");

		mappingDynamicId2StaticId.put("_FP_AND11_FVERGE11_1_AC", "_FP.AND11-FVERGE11-1_AC");
		mappingDynamicId2StaticId.put("_FP_AND11_FVERGE11_2_AC", "_FP.AND11-FVERGE11-2_AC");
		mappingDynamicId2StaticId.put("_FS_BIS11_FVALDI11_1_AC", "_FS.BIS11-FVALDI11-1_AC");
		mappingDynamicId2StaticId.put("_FS_BIS11_FVALDI11_2_AC", "_FS.BIS11-FVALDI11-2_AC");
		mappingDynamicId2StaticId.put("_FSSV_O11_FP_AND11_1_AC", "_FSSV.O11-FP.AND11-1_AC");
		mappingDynamicId2StaticId.put("_FSSV_O11_FP_AND11_2_AC", "_FSSV.O11-FP.AND11-2_AC");
		mappingDynamicId2StaticId.put("_FSSV_O11_FTILL511_1_AC", "_FSSV.O11-FTILL511-1_AC");
		mappingDynamicId2StaticId.put("_FSSV_O11_FTILL511_2_AC", "_FSSV.O11-FTILL511-2_AC");
		mappingDynamicId2StaticId.put("_FTILL511_FS_BIS11_1_AC", "_FTILL511-FS.BIS11-1_AC");
		mappingDynamicId2StaticId.put("_FTILL511_FS_BIS11_2_AC", "_FTILL511-FS.BIS11-2_AC");

		mappingDynamicId2StaticId.put("_FP_AND11_FTDPRA11_1_PT", "_FP.AND11-FTDPRA11-1_PT");

		mappingDynamicId2StaticId.put("_FSSV_T11_SM", "_FSSV.T11_SM");
	}
}
