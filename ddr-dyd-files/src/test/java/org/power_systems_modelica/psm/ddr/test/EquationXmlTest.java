package org.power_systems_modelica.psm.ddr.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.junit.Test;
import org.power_systems_modelica.psm.ddr.dyd.SystemDefinitions;
import org.power_systems_modelica.psm.ddr.dyd.equations.Equal;
import org.power_systems_modelica.psm.ddr.dyd.equations.Equation;
import org.power_systems_modelica.psm.ddr.dyd.equations.ExpressionList;
import org.power_systems_modelica.psm.ddr.dyd.equations.Folding.Sum;
import org.power_systems_modelica.psm.ddr.dyd.equations.Literal;
import org.power_systems_modelica.psm.ddr.dyd.xml.DydXml;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;

public class EquationXmlTest
{
	@Test
	public void testWrite() throws XMLStreamException, IOException
	{
		Equation eq = EquationsTest.buildTestEquation();
		final Path file = DATA_TMP.resolve("eq.xml");

		SystemDefinitions sd = new SystemDefinitions();
		sd.add(eq);
		DydXml.write(file, sd);

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
		final Path file = DATA_TMP.resolve("eq2.xml");
		try (PrintWriter p = new PrintWriter(file.toFile()))
		{

			p.println("<?xml version=\"1.0\"?>");
			p.println("<system_definitions xmlns=\"" + XmlUtil.NAMESPACE + "\">");
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
			p.println("      <startsWith prefix=\"xxx_\"/>");
			p.println("     </or>");
			// Second selector not allowed here, the forAll element must accept only one selector
			p.println("     <startsWith prefix=\"gen_\"/>");
			p.println("     <template variable=\"_g\">_g.omega * _g.SN * _g.Hin</template>");
			p.println("    </forAll>");
			p.println("   </sum>");
			p.println("  </right>");
			p.println(" </equal>");
			p.println("</equation>");
			p.println("</system_definitions>");
		}

		// Should be invalid
		try
		{
			XmlUtil.validate(file);
		}
		catch (Exception x)
		{
			String expected = "cvc-complex-type.2.4.a";
			String msg = x.getCause().getMessage();
			String actual = msg.substring(0, msg.indexOf(':'));
			assertEquals(expected, actual);
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
		final Path file = DATA_TMP.resolve("eq.xml");
		SystemDefinitions sd = new SystemDefinitions();
		sd.add(eq);
		DydXml.write(file, sd);
		SystemDefinitions sd2 = (SystemDefinitions) DydXml.read(file);
		Equation eq2 = sd2.getEquations().get(0);

		String original = eq.writeIn(EquationsTest.contextStrings());
		String fromXml = eq2.writeIn(EquationsTest.contextStrings());

		System.out.println(original);
		System.out.println(fromXml);
		assertEquals(original, fromXml);
	}
	
	private static final Path DATA_TMP = Paths.get(System.getenv("PSM_DATA"))
			.resolve("tmp");
}
