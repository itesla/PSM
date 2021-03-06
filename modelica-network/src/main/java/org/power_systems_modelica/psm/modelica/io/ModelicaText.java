package org.power_systems_modelica.psm.modelica.io;

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
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelicaText
{
	public static String normalize(String mo)
	{
		String mo0 = replace(ALL_LINE_SEPARATORS, mo, "\n");
		String mo1 = remove(COMMENT, mo0);
		String mo2 = replace(INDENT, mo1, "$1");
		String mo3 = replace(WHSP_END, mo2, "$1");
		String mo4 = replace(EMPTY_LINE, mo3, "\n");
		String mo5 = roundNumbers(mo4);
		String mo7 = sortedConnectEquations(mo5);
		String moo = mo7;

		return moo;
	}

	private static String roundNumbers(String mo)
	{
		StringBuffer result = new StringBuffer();
		Matcher m = FLOATING_POINT_NUMBER.matcher(mo);
		while (m.find())
		{
			String snum = m.group("number");
			float num = Float.parseFloat(snum);
			float num1 = Math.round(num * FLOATING_POINT_ROUNDING) / FLOATING_POINT_ROUNDING;
			String replacement = "" + num1;
			if (!snum.equals(replacement)) m.appendReplacement(result, m.group("pre") + replacement
					+ m.group("post"));
		}
		m.appendTail(result);
		return result.toString();
	}

	private static String sortedConnectEquations(String mo)
	{
		List<String> lines = Arrays.asList(mo.split(ALL_LINE_SEPARATORS.toString()));
		List<String> connects = lines.stream()
				.filter(ModelicaText::isConnect)
				.sorted()
				.collect(Collectors.toList());
		List<String> lines1 = new ArrayList<>(lines.size());

		boolean addedConnects = false;
		for (Iterator<String> k = lines.iterator(); k.hasNext();)
		{
			String line = k.next();
			if (isConnect(line))
			{
				if (!addedConnects)
				{
					lines1.addAll(connects);
					addedConnects = true;
				}
			}
			else lines1.add(line);
		}
		return String.join(LINE_SEPARATOR, lines1);
	}

	private static boolean isConnect(String line)
	{
		return line.startsWith("connect");
	}

	private static String remove(Pattern p, String s)
	{
		return p.matcher(s).replaceAll("");
	}

	private static String replace(Pattern p, String s, String r)
	{
		return p.matcher(s).replaceAll(r);
	}

	private static final Pattern	COMMENT					= Pattern.compile("//.*$",
																	Pattern.MULTILINE);
	private static final Pattern	INDENT					= Pattern.compile("^[ \\t]+([^ \\t])",
																	Pattern.MULTILINE);
	private static final Pattern	WHSP_END				= Pattern.compile("([^ \\t])[ \\t]+$",
																	Pattern.MULTILINE);
	private static final Pattern	EMPTY_LINE				= Pattern
																	.compile("(\\n|\\r|\\r\\n){2,}+");
	private static final Pattern	ALL_LINE_SEPARATORS		= Pattern.compile("(\\n|\\r|\\r\\n)");
	private static final Pattern	FLOATING_POINT_NUMBER	= Pattern
																	.compile(
																	"(?<pre>[^a-zA-Z_])(?<number>[\\+\\-]?([0-9]*[.])?[0-9]+([eE][\\+\\-]?[0-9]+)?)(?<post>[^a-zA-Z_])");
	private static final float		FLOATING_POINT_ROUNDING	= 1e6f;

	private static final String		LINE_SEPARATOR			= System.getProperty("line.separator");
}
