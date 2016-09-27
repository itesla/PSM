package org.power_systems_modelica.psm.ddr.dyd.equations;

import java.util.List;
import java.util.function.Predicate;

public abstract class LogicalSelector implements Selector
{
	protected LogicalSelector(List<Selector> selectors)
	{
		this.selectors = selectors;
	}

	public List<Selector> getSelectors()
	{
		return selectors;
	}

	public abstract <T> boolean evaluate(Context<T> context, List<Selector> selectors, T t);

	public static class And extends LogicalSelector
	{
		public And(List<Selector> selectors)
		{
			super(selectors);
		}

		@Override
		public <T> boolean evaluate(Context<T> context, List<Selector> selectors, T t)
		{
			for (Selector s : selectors)
			{
				Predicate<T> predicate = context.getPredicate(s);
				if (!predicate.test(t)) return false;
			}
			return true;
		}
	}

	public static class Or extends LogicalSelector
	{
		public Or(List<Selector> selectors)
		{
			super(selectors);
		}

		@Override
		public <T> boolean evaluate(Context<T> context, List<Selector> selectors, T t)
		{
			for (Selector s : selectors)
			{
				Predicate<T> predicate = context.getPredicate(s);
				if (predicate.test(t)) return true;
			}
			return false;
		}
	}

	public <T> Predicate<T> getPredicate(Context<T> context)
	{
		return (t -> evaluate(context, selectors, t));
	}

	private final List<Selector> selectors;

}
