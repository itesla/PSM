package org.power_systems_modelica.psm.workflow.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import com.google.common.io.ByteStreams;

public class WorkflowTestUtil
{
	public static final Path	DATA_TMP		= Paths
			.get(System.getenv("PSM_DATA"))
			.resolve("tmp");
	public static final Path	TEST_SAMPLES	= Paths
			.get(System.getenv("PSM_DATA"))
			.resolve("test");

	public static void assertEqualsText(InputStream expected, InputStream actual)
			throws IOException
	{
		assertEquals(
				new String(ByteStreams.toByteArray(expected), StandardCharsets.UTF_8),
				new String(ByteStreams.toByteArray(actual), StandardCharsets.UTF_8));
	}

	public static void assertEqualsModelicaText(
			InputStream iexpected,
			InputStream iactual)
			throws IOException
	{
		String expected0 = new String(ByteStreams.toByteArray(iexpected), StandardCharsets.UTF_8);
		String actual0 = new String(ByteStreams.toByteArray(iactual), StandardCharsets.UTF_8);

		String expected = normalizeModelicaText("expected", expected0);
		String actual = normalizeModelicaText("actual", actual0);

		assertEquals(expected, actual);
	}

	public static String normalizeModelicaText(String label, String mo)
	{
		String mo1 = remove(COMMENT, mo);
		String mo2 = replace(INDENT, mo1, "$1");
		String mo3 = replace(WHSP_END, mo2, "$1");
		String mo4 = replace(EMPTY_LINE, mo3, "\n");
		return mo4;
	}

	private static String remove(Pattern p, String s)
	{
		return p.matcher(s).replaceAll("");
	}

	private static String replace(Pattern p, String s, String r)
	{
		return p.matcher(s).replaceAll(r);
	}

	private static final Pattern	COMMENT		= Pattern.compile("//.*$", Pattern.MULTILINE);
	private static final Pattern	INDENT		= Pattern.compile("^[ \\t]+([^ \\t])",
			Pattern.MULTILINE);
	private static final Pattern	WHSP_END	= Pattern.compile("([^ \\t])[ \\t]+$",
			Pattern.MULTILINE);
	private static final Pattern	EMPTY_LINE	= Pattern.compile("(\\n|\\r|\\r\\n){2,}+");
}
