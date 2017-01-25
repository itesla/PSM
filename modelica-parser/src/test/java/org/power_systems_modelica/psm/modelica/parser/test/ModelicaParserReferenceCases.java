package org.power_systems_modelica.psm.modelica.parser.test;

import static org.power_systems_modelica.psm.commons.test.TestUtil.DATA_TMP;
import static org.power_systems_modelica.psm.commons.test.TestUtil.TEST_SAMPLES;

import java.io.PrintWriter;
import java.nio.file.Path;

import org.junit.Test;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;
import org.power_systems_modelica.psm.modelica.test.ModelicaTestUtil;

public class ModelicaParserReferenceCases
{
	@Test
	public void parseIeee14() throws Exception
	{
		parse("ieee14/itesla", "ieee14bus_no_lf.mo");
	}

	@Test
	public void parseIeee30() throws Exception
	{
		parse("ieee30/itesla", "ieee30bus_no_lf.mo");
	}

	@Test
	public void parseIeee57() throws Exception
	{
		parse("ieee57/itesla", "ieee57bus_no_lf.mo");
	}

	@Test
	public void parseIeee118() throws Exception
	{
		parse("ieee118/itesla", "ieee118bus_no_lf.mo");
	}

	@Test
	public void parseSmallCase1() throws Exception
	{
		parse("smallcase1/itesla", "case1_no_lf.mo");
	}

	@Test
	public void parseSmallCase2() throws Exception
	{
		parse("smallcase2/itesla", "case2_no_lf.mo");
	}

	@Test
	public void parseSmallCase3() throws Exception
	{
		parse("smallcase3/itesla", "case3_no_lf.mo");
	}

	@Test
	public void parse7buses() throws Exception
	{
		parse("7buses/itesla", "M7buses_no_lf.mo");
	}

	@Test
	public void parseNordic32() throws Exception
	{
		parse("Nordic32/itesla", "Nordic32_no_lf.mo");
	}

	protected void parse(String foldername, String moname) throws Exception
	{
		Path folder = TEST_SAMPLES.resolve(foldername);
		Path moGiven = folder.resolve(moname);
		Path moParsed = DATA_TMP.resolve("parsed_" + moname);

		ModelicaDocument mo = ModelicaParser.parse(moGiven);

		ModelicaTextPrinter mop = new ModelicaTextPrinter(mo);
		mop.setIncludePsmAnnotations(false);
		try (PrintWriter out = new PrintWriter(moParsed.toFile());)
		{
			mop.print(out);
		}

		Path expected = moGiven;
		Path actual = moParsed;

		ModelicaTestUtil.assertEqualsNormalizedModelicaText(expected, actual);
	}
}
