package org.power_systems_modelica.psm.ddr.dyd.equations;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ForAll implements Factors
{
	public ForAll(Selector selector, ExpressionTemplate template)
	{
		this.selector = selector;
		this.template = template;
	}

	public <T> List<Expression> getFrom(Context<T> context)
	{
		// Filter the whole domain of the context using the predicate of the selector
		// Then apply equation template to all selected objects
		// Return the list of obtained Expressions

		// The context is who knows how to build a concrete predicate from the selector definition
		// The selector does not have a type, it only contains a definition of the selection
		// The context is responsible of building a typed predicate based on the definition of the selection
		Predicate<T> predicate = context.getPredicate(selector);

		return context
				.getDomainStream()
				.filter(predicate)
				.map(o -> template.apply(o, context))
				.collect(Collectors.toList());
	}

	public Selector getSelector()
	{
		return selector;
	}

	public ExpressionTemplate getTemplate()
	{
		return template;
	}

	@Override
	public String toString()
	{
		return selector + " " + template;
	}

	private final Selector				selector;
	private final ExpressionTemplate	template;
}