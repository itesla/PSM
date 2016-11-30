package org.power_systems_modelica.psm.ddr.dyd.equations;

public class ExpressionTemplate
{
	public ExpressionTemplate(String variable, String template)
	{
		this.variable = variable;
		this.template = template;
	}

	public String getVariable()
	{
		return variable;
	}

	public String getTemplate()
	{
		return template;
	}

	<T> Expression apply(T object, Context<T> context)
	{
		String e = template.replace(variable, context.write(object));
		return new Literal(e);
	}

	@Override
	public String toString()
	{
		return variable + " : " + template;
	}

	private final String	variable;
	private final String	template;
}