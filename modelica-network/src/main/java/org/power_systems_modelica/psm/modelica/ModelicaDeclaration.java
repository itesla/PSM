package org.power_systems_modelica.psm.modelica;

/*
 * #%L
 * Modelica network model
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelicaDeclaration extends BaseModelicaDeclaration
{
	public ModelicaDeclaration(
			String type,
			String id,
			List<ModelicaArgument> arguments,
			boolean isParameter,
			Annotation a)
	{
		super(type, id, false, isParameter,
				a == null ? new Annotation(new AnnotationItem(DEFAULT_ANNOTATION)) : a);
		Objects.requireNonNull(arguments);
		this.arguments = arguments;
	}

	@Override
	public Object getValue()
	{
		return null;
	}

	@Override
	public List<ModelicaArgument> getArguments()
	{
		if (arguments != null) return Collections.unmodifiableList(arguments);
		return null;
	}

	public void replaceArguments(List<ModelicaArgument> args1)
	{
		this.arguments = args1;
	}

	public boolean containsAnyReference()
	{
		return arguments.stream().anyMatch(a -> a instanceof ModelicaArgumentReference);
	}

	private List<ModelicaArgument>	arguments;
	private static final String		DEFAULT_ANNOTATION	= "Placement(transformation())";
}
