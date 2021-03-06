package org.power_systems_modelica.psm.modelica.test;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.modelica.BaseModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.io.ModelicaText;

import com.google.common.io.ByteStreams;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelicaTestUtil
{
	public static void assertSameDeclarationsAndEquations(
			ModelicaModel expected,
			ModelicaModel actual)
	{
		assertSameDeclarations(expected.getDeclarations(), actual.getDeclarations());
		assertSameEquations(expected.getEquations(), actual.getEquations());
	}

	public static void assertSameDeclarations(
			List<BaseModelicaDeclaration> expected,
			List<BaseModelicaDeclaration> actual)
	{
		// When checking declarations we take into account that parameter references may have been resolved
		assertEquals(expected.size(), actual.size());
		for (int k = 0; k < expected.size(); k++)
		{
			BaseModelicaDeclaration de = expected.get(k);
			BaseModelicaDeclaration da = actual.get(k);
			assertEquals(de.getType(), da.getType());
			assertEquals(de.getId(), da.getId());
			if (de.getAnnotation() != null)
				assertEquals(de.getAnnotation().asText(), da.getAnnotation().asText());
			assertEquals(de.getValue(), da.getValue());
			if (de.getArguments() == null)
			{
				assertTrue(da.getArguments() == null);
			}
			else
			{
				assertEquals(de.getArguments().size(), da.getArguments().size());
				for (int j = 0; j < de.getArguments().size(); j++)
				{
					ModelicaArgument pe = de.getArguments().get(j);
					ModelicaArgument pa = da.getArguments().get(j);
					assertEquals(pe.getName(), pa.getName());
					if (pe instanceof ModelicaArgumentReference) continue;
					if (pa instanceof ModelicaArgumentReference) continue;
					assertEquals(pe.getValue(), pa.getValue());
				}
			}
		}
	}

	public static void assertSameEquations(
			List<ModelicaEquation> expected,
			List<ModelicaEquation> actual)
	{
		assertEquals(expected, actual);
	}

	public static void assertEqualsNormalizedModelicaText(
			Path expectedp,
			Path actualp)
			throws IOException
	{
		InputStream expectedi = Files.newInputStream(expectedp);
		InputStream actuali = Files.newInputStream(actualp);

		String expected0 = new String(ByteStreams.toByteArray(expectedi), StandardCharsets.UTF_8);
		String actual0 = new String(ByteStreams.toByteArray(actuali), StandardCharsets.UTF_8);
		String expected = ModelicaText.normalize(expected0);
		String actual = ModelicaText.normalize(actual0);
		if (DEBUG_MO)
		{
			try (PrintWriter oute = new PrintWriter(PathUtils.DATA_TMP.resolve("expected.mo").toFile()))
			{
				oute.print(expected);
			}
			try (PrintWriter outa = new PrintWriter(PathUtils.DATA_TMP.resolve("actual.mo").toFile()))
			{
				outa.print(actual);
			}
		}
		assertEquals(expected, actual);
	}

	private static final boolean DEBUG_MO = false;
}
