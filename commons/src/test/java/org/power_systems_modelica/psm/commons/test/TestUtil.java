package org.power_systems_modelica.psm.commons.test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TestUtil
{
	public static final Path	TEST_SAMPLES	= Paths
			.get(System.getenv("PSM_DATA"))
			.resolve("test");
	public static final Path	TEST_PRIVATE	= Paths
			.get(System.getenv("PSM_DATA"))
			.resolve("test_private");
	public static final Path	DATA_TMP		= Paths
			.get(System.getenv("PSM_DATA"))
			.resolve("tmp");
	public static final Path	DATA		= Paths
			.get(System.getenv("PSM_DATA"));
	public static final String	NEW_LINE		= System.getProperty("line.separator").toString();
}
