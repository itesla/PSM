package org.power_systems_modelica.psm.modelica.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.power_systems_modelica.psm.modelica.io.ModelicaText;

import com.google.common.io.ByteStreams;

public class ModelicaTestUtil
{
	public static final Path DATA_TMP = Paths
			.get(System.getenv("PSM_DATA"))
			.resolve("tmp");

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
			try (PrintWriter oute = new PrintWriter(DATA_TMP.resolve("expected.mo").toFile()))
			{
				oute.print(expected);
			}
			try (PrintWriter outa = new PrintWriter(DATA_TMP.resolve("actual.mo").toFile()))
			{
				outa.print(actual);
			}
		}
		assertEquals(expected, actual);
	}

	private static final boolean DEBUG_MO = false;
}
