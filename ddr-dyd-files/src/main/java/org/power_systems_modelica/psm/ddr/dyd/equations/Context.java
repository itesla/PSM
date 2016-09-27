package org.power_systems_modelica.psm.ddr.dyd.equations;

import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class Context<T>
{
	public abstract String write(T object);

	public abstract Stream<T> getDomainStream();

	public Predicate<T> getPredicate(Selector selector)
	{
		if (selector instanceof LogicalSelector)
		{
			Context<T> ctx = this;
			Predicate<T> p = ((LogicalSelector) selector).getPredicate(ctx);
			return s -> p.test(s);
		}
		return null;
	}
}
