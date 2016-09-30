package org.power_systems_modelica.psm.ddr;

public class IidmNames
{
	// FIXME This should go in a configuration file
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
				return "A";
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
				return "A";
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
		case "Generator":
			switch(mname)
			{
			case "ur0":
				return "re(u0)";
			case "ui0":
				return "im(u0)";
			case "V2":
				return "Vnom";
			}
		}
		return null;
	}
}
