package org.power_systems_modelica.psm.modelica;

public class ModelicaTricks
{
	public static String getKind(String name)
	{
		String kind = name.substring(0, name.indexOf("_"));
		if (kind == null) kind = "other";
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
		String otherName = other.getModelInstantiations().get(0).getName();
		if (otherName.startsWith("bus_"))
		{
			bus = other;
			branch = m;
		}

		String branchName = branch.getModelInstantiations().get(0).getName();
		String idBus = bus.getStaticId().replace("_TN", "");
		int pBus = branchName.indexOf(idBus);
		int pBusFrom = branchName.indexOf("__") + 1;
		boolean isBusFrom = pBus == pBusFrom;

		return isBusFrom ? conns[0] : conns[1];
	}
}
