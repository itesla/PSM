package org.power_systems_modelica.psm.ddr.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.power_systems_modelica.psm.ddr.dyd.Component;
import org.power_systems_modelica.psm.ddr.dyd.Connection;
import org.power_systems_modelica.psm.ddr.dyd.Connector;
import org.power_systems_modelica.psm.ddr.dyd.Model;
import org.power_systems_modelica.psm.ddr.dyd.ModelForAssociation;
import org.power_systems_modelica.psm.ddr.dyd.ModelForElement;
import org.power_systems_modelica.psm.ddr.dyd.ModelForEvent;
import org.power_systems_modelica.psm.ddr.dyd.ModelForType;
import org.power_systems_modelica.psm.ddr.dyd.equations.Equation;

public class DynamicDataRepositoryTestUtil
{
	public static void assertSameModelDefinitions(List<Model> expected, List<Model> actual)
	{
		System.out.println("model definitions");
		assertEquals(expected.size(), actual.size());
		for (int k = 0; k < expected.size(); k++)
		{
			Model me = expected.get(k);
			Model ma = actual.get(k);
			assertSameModelProperties(me, ma);
			assertSameComponents(me, ma);
			assertSameConnections(me, ma);
			assertSameConnectors(me, ma);
		}
	}

	public static void assertSameModelProperties(Model me, Model ma)
	{
		assertEquals(me.getClass(), ma.getClass());
		assertEquals(me.getId(), ma.getId());
		assertEquals(me.getStage(), ma.getStage());
		System.out.println(me.getId());
		if (me instanceof ModelForAssociation)
		{
			System.out.println("    " + ((ModelForAssociation) me).getAssociation());
			assertEquals(
					((ModelForAssociation) me).getAssociation(),
					((ModelForAssociation) ma).getAssociation());
		}
		else if (me instanceof ModelForElement)
		{
			System.out.println("    " + ((ModelForElement) me).getStaticId());
			assertEquals(
					((ModelForElement) me).getStaticId(),
					((ModelForElement) ma).getStaticId());
		}
		else if (me instanceof ModelForType)
		{
			System.out.println("    " + ((ModelForType) me).getType());
			assertEquals(
					((ModelForType) me).getType(),
					((ModelForType) ma).getType());
		}
		else if (me instanceof ModelForEvent)
		{
			System.out.println("    " +
					((ModelForEvent) me).getEvent() + " " +
					((ModelForEvent) me).getInjection());
			assertEquals(
					((ModelForEvent) me).getEvent(),
					((ModelForEvent) ma).getEvent());
			assertEquals(
					((ModelForEvent) me).getInjection(),
					((ModelForEvent) ma).getInjection());
		}
	}

	public static void assertSameComponents(Model me, Model ma)
	{
		assertEquals(me.getComponents().size(), ma.getComponents().size());
		if (me.getComponents().size() > 0)
		{
			System.out.println("    components " + me.getComponents().size());
			for (int j = 0; j < me.getComponents().size(); j++)
			{
				Component ce = me.getComponents().get(j);
				Component ca = ma.getComponents().get(j);
				System.out.println("        " + ce.getName() + " : " + ce.getId());
				assertEquals(ce.getName(), ca.getName());
				assertEquals(ce.getId(), ca.getId());
			}
		}
	}

	public static void assertSameConnections(Model me, Model ma)
	{
		assertEquals(me.getConnections().size(), ma.getConnections().size());
		if (me.getConnections().size() > 0)
		{
			System.out.println("    connections " + me.getConnections().size());
			for (int j = 0; j < me.getConnections().size(); j++)
			{
				Connection ce = me.getConnections().get(j);
				Connection ca = ma.getConnections().get(j);
				System.out.println("        " +
						ce.getId1() + "." + ce.getVar1() + " - " +
						ce.getId2() + "." + ce.getVar2());
				assertEquals(ce.getId1(), ca.getId1());
				assertEquals(ce.getVar1(), ca.getVar1());
				assertEquals(ce.getId2(), ca.getId2());
				assertEquals(ce.getVar2(), ca.getVar2());
			}
		}
	}

	public static void assertSameConnectors(Model me, Model ma)
	{
		assertEquals(me.getConnectors().size(), ma.getConnectors().size());
		if (me.getConnectors().size() > 0)
		{
			System.out.println("    connectors " + me.getConnectors().size());
			for (int j = 0; j < me.getConnectors().size(); j++)
			{
				Connector ce = me.getConnectors().get(j);
				Connector ca = ma.getConnectors().get(j);
				System.out.println("        " +
						ce.getId() + "." + ce.getPin() + " --> " + ce.getTarget());
				assertEquals(ce.getId(), ca.getId());
				assertEquals(ce.getPin(), ca.getPin());
				assertEquals(ce.getTarget(), ca.getTarget());
			}
		}
	}

	public static void assertSameEquationDefinitions(
			List<Equation> expected,
			List<Equation> actual)
	{
		assertEquals(expected.size(), actual.size());
		System.out.println("equations " + expected.size());
		for (int j = 0; j < expected.size(); j++)
		{
			Equation eqe = expected.get(j);
			Equation eqa = actual.get(j);
			System.out.println("    " + eqe.toString());

			assertEquals(eqe.toString(), eqa.toString());
		}
	}
}
