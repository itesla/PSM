package org.power_systems_modelica.psm.commons;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Errors
{
	public static String getStackTrace(Throwable x)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		x.printStackTrace(pw);
		pw.flush();
		sw.flush();
		return sw.toString();
	}
}
