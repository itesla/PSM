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
				return "V_pu";
			case "angle_0":
				return "A";
			}
		case "Line":
			switch (mname)
			{
			case "R":
				return "R_pu";
			case "X":
				return "X_pu";
			case "G":
				return "G1_pu";
			case "B":
				return "B1_pu";
			}
		case "Load":
			switch (mname)
			{
			case "V_0":
				return "V_pu";
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
				return "B_pu";
			case "nsteps":
				return "MaximumSectionCount";
			}
		case "Transformer":
			switch (mname)
			{
			case "r":
				return "ratio";
			case "G0":
				return "G_pu";
			case "B0":
				return "B_pu";
			case "R":
				return "R_pu";
			case "X":
				return "X_pu";
			}
		case "Generator":
			switch(mname)
			{
			case "ur0":
				return "u0_re";
			case "ui0":
				return "u0_im";
			case "V2":
				return "Vnom";
			}
		}
		return null;
	}
}
