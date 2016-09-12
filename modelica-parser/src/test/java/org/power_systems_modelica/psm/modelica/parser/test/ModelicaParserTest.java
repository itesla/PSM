package org.power_systems_modelica.psm.modelica.parser.test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;

public class ModelicaParserTest
{
	@Test
	public void testParserMini() throws FileNotFoundException, IOException
	{
		ModelicaDocument mo = parse("mini.mo");

		assertEquals("N44_h21", mo.getSystemModel().getName());
		assertEquals(1, mo.getSystemModel().getParameters().size());
		assertEquals(6, mo.getSystemModel().getModelInstantiations().size());
		String first = "bus__f17695ab_9aeb_11e5_91da_b8763fd99c5f";
		assertEquals(first, mo.getSystemModel().getModelInstantiations().get(0).getName());
		assertEquals(2, mo.getSystemModel().getEquations().size());
	}

	@Test
	public void testParserN44() throws FileNotFoundException, IOException
	{
		ModelicaDocument mo = parse("N44_h21.mo");
		assertEquals("N44_h21", mo.getSystemModel().getName());
		assertEquals(1, mo.getSystemModel().getParameters().size());
		assertEquals(475, mo.getSystemModel().getModelInstantiations().size());
		String first = "bus__f17695ab_9aeb_11e5_91da_b8763fd99c5f";
		assertEquals(first, mo.getSystemModel().getModelInstantiations().get(0).getName());
		String firsta0name = "V_0";
		String firsta0value = "1.0";
		assertEquals(firsta0name, mo.getSystemModel().getModelInstantiations().get(0).getArguments()
				.get(0).getName());
		assertEquals(firsta0value, mo.getSystemModel().getModelInstantiations().get(0)
				.getArguments().get(0).getValue());
		assertEquals(1175, mo.getSystemModel().getEquations().size());
	}

	private static final Path TEST_SAMPLES = Paths.get(System.getenv("PSM_DATA"))
			.resolve("test")
			.resolve("modelica-parser");

	private ModelicaDocument parse(String name) throws FileNotFoundException, IOException
	{
		Path mo = TEST_SAMPLES.resolve(name);
		return new ModelicaParser().parse(mo);
	}
}
