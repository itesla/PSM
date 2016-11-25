package org.power_systems_modelica.psm.workflow.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

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
}
