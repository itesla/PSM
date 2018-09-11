package org.power_systems_modelica.psm.commons;

/*
 * #%L
 * Commons
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
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
