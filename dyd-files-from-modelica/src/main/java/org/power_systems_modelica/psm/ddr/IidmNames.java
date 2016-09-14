package org.power_systems_modelica.psm.ddr;

public class IidmNames
{
	public static String getIidmNameForModelicaArgument(String stype, String mname)
	{
		switch (stype)
		{
		case "Bus":
			switch (mname)
			{
			case "V_0":
				return "pu(V)";
			case "angle_0":
				return "Angle";
			}
		case "Line":
			switch (mname)
			{
			case "R":
				return "pu(R)";
			case "X":
				return "pu(X)";
			case "G":
				return "pu(G1)";
			case "B":
				return "pu(B1)";
			}
		case "Load":
			switch (mname)
			{
			case "V_0":
				return "pu(V)";
			case "P_0":
				return "P0";
			case "Q_0":
				return "Q0";
			case "angle_0":
				return "angle";
			}
		case "Shunt":
			switch (mname)
			{
			case "B":
				return "pu(B)";
			case "nsteps":
				return "MaximumSectionCount";
			}
		case "Transformer":
			switch (mname)
			{
			case "r":
				return "ratio";
			case "G0":
				return "pu(G)";
			case "B0":
				return "pu(B)";
			case "R":
				return "pu(R)";
			case "X":
				return "pu(X)";
			}
		}
		return null;
	}
}
