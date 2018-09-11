package org.power_systems_modelica.psm.dd.equations;

/*
 * #%L
 * Dynamic Data
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
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