package org.power_systems_modelica.psm.ddr.test;

/*
 * #%L
 * Dynamic Data Repository on DYD files
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
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.junit.Test;
import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.dd.Model;
import org.power_systems_modelica.psm.dd.equations.Equal;
import org.power_systems_modelica.psm.dd.equations.Equation;
import org.power_systems_modelica.psm.dd.equations.ExpressionList;
import org.power_systems_modelica.psm.dd.equations.Folding.Sum;
import org.power_systems_modelica.psm.dd.equations.Literal;
import org.power_systems_modelica.psm.ddr.dyd.ModelContainer;
import org.power_systems_modelica.psm.ddr.dyd.xml.DydXml;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class EquationXmlTest
{
	@Test
	public void testWrite() throws XMLStreamException, IOException
	{
		Equation eq = EquationsTest.buildTestEquation();
		final Path file = PathUtils.DATA_TMP.resolve("eq.xml");

		ModelContainer mc = new ModelContainer();
		Model m = new Model("DMSystem");
		m.addOtherEquation(eq);
		DydXml.write(file, mc);

		List<String> lines = Files.readAllLines(file);
		int count = 0;
		for (String line : lines)
			System.out.printf("%2d %s%n", count++, line);
	}

	@Test
	public void testRoundTripMainTestEquation() throws XMLStreamException, IOException
	{
		Equation eq = EquationsTest.buildTestEquation();
		testRoundTrip(eq);

	}

	@Test
	public void testReadInvalid() throws XMLStreamException, IOException
	{
		final Path file = PathUtils.DATA_TMP.resolve("eq2.xml");
		try (PrintWriter p = new PrintWriter(file.toFile()))
		{

			p.println("<?xml version=\"1.0\"?>");
			p.println("<model_container xmlns=\"" + XmlUtil.NAMESPACE + "\">");
			p.println("<model>");
			p.println("<equation>");
			p.println(" <equal>");
			p.println("  <left>");
			p.println("   <literal>omega</literal>");
			p.println("  </left>");
			p.println("  <right>");
			p.println("   <sum>");
			p.println("    <forAll>");
			p.println("     <or>");
			p.println("      <startsWith prefix=\"gen_\"/>");
			p.println("      <startsWith prefix=\"kkk_\"/>");
			p.println("     </or>");
			// Second selector not allowed here, the forAll element must accept only one selector
			p.println("     <startsWith prefix=\"gen_\"/>");
			p.println("     <template variable=\"_g\">_g.omega * _g.SN * _g.Hin</template>");
			p.println("    </forAll>");
			p.println("   </sum>");
			p.println("  </right>");
			p.println(" </equal>");
			p.println("</equation>");
			p.println("</model>");
			p.println("</model_container>");
		}

		// Should be invalid
		try
		{
			XmlUtil.validate(file);
		}
		catch (Exception x)
		{
			String expectedReason = "cvc-complex-type.2.4.a";
			String msg = x.getCause().getMessage();
			String actualReason = msg.substring(0, msg.indexOf(':'));
			assertEquals(expectedReason, actualReason);
			assertTrue(msg.contains("'startsWith'"));
		}
	}

	@Test
	public void testFactorsAsExpressionList() throws XMLStreamException, IOException
	{
		ExpressionList xs = new ExpressionList();
		xs.add(new Literal("x1"));
		xs.add(new Literal("x2"));
		xs.add(new Literal("x3"));
		Equation eq = new Equal(new Literal("y"), new Sum(xs));
		testRoundTrip(eq);
	}

	private void testRoundTrip(Equation eq) throws XMLStreamException, IOException
	{
		final Path file = PathUtils.DATA_TMP.resolve("eq.xml");

		ModelContainer mc = new ModelContainer();
		Model m = new Model("DMSystem");
		m.addOtherEquation(eq);
		mc.add(m);
		DydXml.write(file, mc);
		ModelContainer mc2 = (ModelContainer) DydXml.read(file);
		Equation eq2 = mc2.getModels().get(0).getOtherEquations().get(0);

		String original = eq.writeIn(EquationsTest.contextStrings());
		String fromXml = eq2.writeIn(EquationsTest.contextStrings());

		System.out.println(original);
		System.out.println(fromXml);
		assertEquals(original, fromXml);
	}
}
