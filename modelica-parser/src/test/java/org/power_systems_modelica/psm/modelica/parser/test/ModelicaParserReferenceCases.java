package org.power_systems_modelica.psm.modelica.parser.test;

import static org.power_systems_modelica.psm.commons.test.TestUtil.DATA;
import static org.power_systems_modelica.psm.commons.test.TestUtil.DATA_TMP;

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
		parse("test", "ieee14/itesla", "ieee14bus_no_lf.mo");
	}

	@Test
	public void parseIeee30() throws Exception
	{
		parse("test", "ieee30/itesla", "ieee30bus_no_lf.mo");
	}

	@Test
	public void parseIeee57() throws Exception
	{
		parse("test", "ieee57/itesla", "ieee57bus_no_lf.mo");
	}

	@Test
	public void parseIeee118() throws Exception
	{
		parse("test_private", "ieee118/itesla", "ieee118bus_no_lf.mo");
	}

	@Test
	public void parseSmallCase1() throws Exception
	{
		parse("test", "smallcase1/itesla", "case1_no_lf.mo");
	}

	@Test
	public void parseSmallCase2() throws Exception
	{
		parse("test", "smallcase2/itesla", "case2_no_lf.mo");
	}

	@Test
	public void parseSmallCase3() throws Exception
	{
		parse("test", "smallcase3/itesla", "case3_no_lf.mo");
	}

	@Test
	public void parse7buses() throws Exception
	{
		parse("test", "7buses/itesla", "M7buses_no_lf.mo");
	}

	@Test
	public void parseNordic32() throws Exception
	{
		parse("test", "Nordic32/itesla", "Nordic32_no_lf.mo");
	}

	protected void parse(String catalog, String foldername, String moname) throws Exception
	{
		Path folder = DATA.resolve(catalog).resolve(foldername);
		Path moGiven = folder.resolve(moname);
		Path moParsed = DATA_TMP.resolve("parsed_" + moname);

		ModelicaDocument mo = ModelicaParser.parse(moGiven);

		boolean includePsmAnnotations = false;
		ModelicaTextPrinter.print(mo, moParsed, includePsmAnnotations);

		Path expected = moGiven;
		Path actual = moParsed;

		ModelicaTestUtil.assertEqualsNormalizedModelicaText(expected, actual);
	}
}
