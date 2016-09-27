package org.power_systems_modelica.psm.ddr.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.junit.Test;
import org.power_systems_modelica.psm.ddr.dyd.equations.Context;
import org.power_systems_modelica.psm.ddr.dyd.equations.Equal;
import org.power_systems_modelica.psm.ddr.dyd.equations.Equation;
import org.power_systems_modelica.psm.ddr.dyd.equations.Expression;
import org.power_systems_modelica.psm.ddr.dyd.equations.ExpressionList;
import org.power_systems_modelica.psm.ddr.dyd.equations.ExpressionTemplate;
import org.power_systems_modelica.psm.ddr.dyd.equations.Factors;
import org.power_systems_modelica.psm.ddr.dyd.equations.Folding.Sum;
import org.power_systems_modelica.psm.ddr.dyd.equations.ForAll;
import org.power_systems_modelica.psm.ddr.dyd.equations.Literal;
import org.power_systems_modelica.psm.ddr.dyd.equations.LogicalSelector;
import org.power_systems_modelica.psm.ddr.dyd.equations.PrefixSelector;
import org.power_systems_modelica.psm.ddr.dyd.equations.Quotient;
import org.power_systems_modelica.psm.ddr.dyd.equations.Selector;

public class EquationsTest
{
	private static final String STR_WEIGHTED_AVERAGE_OF_OMEGAS = "(gen_1.omega * gen_1.SN * gen_1.Hin + gen_2.omega * gen_2.SN * gen_2.Hin) / (gen_1.SN * gen_1.Hin + gen_2.SN * gen_2.Hin)";

	@Test
	public void testExpressionStrings()
	{
		Expression e = weightedAverageOfGeneratorOmegas();
		assertEquals(STR_WEIGHTED_AVERAGE_OF_OMEGAS, e.writeIn(contextStrings()));
	}

	@Test
	public void testEquationStrings()
	{
		Equation eq = buildTestEquation();
		assertEquals(
				"omega = " + STR_WEIGHTED_AVERAGE_OF_OMEGAS,
				eq.writeIn(contextStrings()));
	}

	@Test
	public void testExpressionDynamicModels()
	{
		Expression e = weightedAverageOfGeneratorOmegas();
		assertEquals(STR_WEIGHTED_AVERAGE_OF_OMEGAS, e.writeIn(contextDynamicModels()));
	}

	@Test
	public void testEquationDynamicModels()
	{
		Equation eq = buildTestEquation();
		assertEquals(
				"omega = " + STR_WEIGHTED_AVERAGE_OF_OMEGAS,
				eq.writeIn(contextDynamicModels()));
	}

	@Test
	public void testFactorsAsExpressionList()
	{
		ExpressionList xs = new ExpressionList();
		xs.add(new Literal("x1"));
		xs.add(new Literal("x2"));
		xs.add(new Literal("x3"));
		Equation eq = new Equal(new Literal("y"), new Sum(xs));

		assertEquals("y = (x1 + x2 + x3)", eq.writeIn(contextStrings()));
	}

	public static final Equation buildTestEquation()
	{
		Equation eq = new Equal(
				new Literal("omega"),
				weightedAverageOfGeneratorOmegas());
		return eq;
	}

	private static final Expression weightedAverageOfGeneratorOmegas()
	{
		Selector selector1 = new PrefixSelector("gen_");
		Selector selector2 = new PrefixSelector("xxx_");
		Selector or = new LogicalSelector.Or(Arrays.asList(selector1, selector2));
		Selector selector = or;

		ExpressionTemplate t1 = new ExpressionTemplate(
				"_g",
				"_g.omega * _g.SN * _g.Hin");
		ExpressionTemplate t2 = new ExpressionTemplate(
				"_g",
				"_g.SN * _g.Hin");
		Factors f1 = new ForAll(selector, t1);
		Factors f2 = new ForAll(selector, t2);
		Sum s1 = new Sum(f1);
		Sum s2 = new Sum(f2);
		Quotient q = new Quotient(s1, s2);
		return q;
	}

	private static final List<String> TEST_ELEMENT_IDS = Arrays.asList(
			"bus_1",
			"gen_1",
			"load_1",
			"bus_2",
			"gen_2");

	public static Context<String> contextStrings()
	{
		return new Context<String>()
		{
			@Override
			public String write(String s)
			{
				return s;
			}

			@Override
			public Stream<String> getDomainStream()
			{
				return TEST_ELEMENT_IDS.stream();
			}

			@Override
			public Predicate<String> getPredicate(Selector selector)
			{
				Predicate<String> p = super.getPredicate(selector);
				if (p != null) return p;
				if (selector instanceof PrefixSelector)
					return s -> s.startsWith(((PrefixSelector) selector).getPrefix());
				throw new RuntimeException("Context<String> can't handle selectors of type "
						+ selector.getClass().getName());
			}
		};
	}

	public static class DynamicModel
	{
		public DynamicModel(String id)
		{
			this.id = id;
		}

		public String getId()
		{
			return id;
		}

		public String toString()
		{
			return getId();
		}

		private final String id;
	}

	public static Context<DynamicModel> contextDynamicModels()
	{
		return new Context<DynamicModel>()
		{
			@Override
			public String write(DynamicModel dm)
			{
				return dm.getId();
			}

			@Override
			public Stream<DynamicModel> getDomainStream()
			{
				return TEST_ELEMENT_IDS
						.stream()
						.map(e -> new DynamicModel(e));
			}

			@Override
			public Predicate<DynamicModel> getPredicate(Selector selector)
			{
				Predicate<DynamicModel> p = super.getPredicate(selector);
				if (p != null) return p;
				if (selector instanceof PrefixSelector)
					return dm -> dm.getId().startsWith(((PrefixSelector) selector).getPrefix());
				return null;
			}
		};
	}
}
