package org.power_systems_modelica.psm.mo2dyd;

import org.power_systems_modelica.psm.ddr.StaticType;

public class IidmNames
{
	// FIXME This should go in a configuration file
	public static String getIidmNameForModelicaArgument(StaticType stype, String mname)
	{
		switch (stype)
		{
		case Bus:
			switch (mname)
			{
			case "V_0":
				return "V_pu";
			case "angle_0":
				return "A";
			default:
				return null;
			}
		case Line:
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
			default:
				return null;
			}
		case Load:
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
			default:
				return null;
			}
		case Shunt:
			switch (mname)
			{
			case "B":
				return "B_pu";
			case "nsteps":
				return "CurrentSectionCount";
			default:
				return null;
			}
		case Transformer:
			switch (mname)
			{
			case "r":
				return "ratio";
			case "theta":
				return "theta";
			case "G0":
			case "G":
				return "G_pu";
			case "B":
			case "B0":
				return "B_pu";
			case "R":
				return "R_pu";
			case "X":
				return "X_pu";
			default:
				return null;
			}
		case Generator:
			switch (mname)
			{
			case "ur0":
				return "u0_re";
			case "ui0":
				return "u0_im";
			case "V2":
				return "Vnom";
			case "p0":
				return "-P_pu";
			case "q0":
				return "-Q_pu";
			case "V_0":
				return "V_pu";
			case "angle_0":
				return "A";
			case "P":
				return "P_pu";
			case "Q":
				return "Q_pu";
			default:
				return null;
			}
		default:
			return null;
		}
	}
}
