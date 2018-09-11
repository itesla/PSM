package org.power_systems_modelica.psm.modelica.parser.test;

/*
 * #%L
 * Modelica simple model parser
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */


import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import org.junit.Test;
import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelicaParserTest
{
	@Test
	public void testParserMini() throws FileNotFoundException, IOException
	{
		ModelicaDocument mo = parse("mini.mo");

		assertEquals("N44_h21", mo.getSystemModel().getId());
		assertEquals(7, mo.getSystemModel().getDeclarations().size());
		assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());
		assertEquals("bus__f17695ab_9aeb_11e5_91da_b8763fd99c5f",
				mo.getSystemModel().getDeclarations().get(1).getId());
		assertEquals(2, mo.getSystemModel().getEquations().size());
	}

	@Test
	public void testParserN44() throws FileNotFoundException, IOException
	{
		ModelicaDocument mo = parse("N44_h21.mo");
		assertEquals("N44_h21", mo.getSystemModel().getId());
		assertEquals(476, mo.getSystemModel().getDeclarations().size());
		assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());
		assertEquals("bus__f17695ab_9aeb_11e5_91da_b8763fd99c5f",
				mo.getSystemModel().getDeclarations().get(1).getId());
		String firsta0name = "V_0";
		String firsta0value = "1.0";
		assertEquals(firsta0name, mo.getSystemModel().getDeclarations().get(1).getArguments()
				.get(0).getName());
		assertEquals(firsta0value, mo.getSystemModel().getDeclarations().get(1)
				.getArguments().get(0).getValue());
		assertEquals(1175, mo.getSystemModel().getEquations().size());
	}

	@Test
	public void testParseGenerator() throws FileNotFoundException, IOException
	{
		ModelicaDocument mo = parse("generator.mo");
		assertEquals("one_generator", mo.getSystemModel().getId());
		assertEquals(3, mo.getSystemModel().getDeclarations().size());
		assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());
		assertEquals("gen_pwGeneratorM2S__GEN____1_SM",
				mo.getSystemModel().getDeclarations().get(1).getId());
		assertEquals(4, mo.getSystemModel().getEquations().size());
		assertEquals(4, mo.getSystemModel().getAnnotation().getItems().size());
	}



	private ModelicaDocument parse(String name) throws FileNotFoundException, IOException
	{
		Path mo = PathUtils.DATA_TEST
				.resolve("modelica-parser")
				.resolve(name);
		return ModelicaParser.parse(mo);
	}

	private ModelicaDocument parse(String catalog, String folder, String name)
			throws FileNotFoundException, IOException
	{
		Path mo = PathUtils.DATA
				.resolve(catalog)
				.resolve(folder)
				.resolve(name);
		return ModelicaParser.parse(mo);
	}
}
