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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelicaAssignment extends BaseModelicaDeclaration
{

	public ModelicaAssignment(
			String type,
			String id,
			Object value,
			boolean isParameter,
			Annotation annotation)
	{
		// No default annotation for assignments
		super(type, id, true, isParameter, annotation);
		this.value = value;
	}

	@Override
	public Object getValue()
	{
		return value; 
	}

	@Override
	public List<ModelicaArgument> getArguments()
	{
		// Nothing to return for assignments
		return new ArrayList<ModelicaArgument>();
	}
	
	@Override
	public void replaceArguments(List<ModelicaArgument> args1)
	{
		// Nothing to do for assignments
	}

	@Override
	public boolean containsAnyReference()
	{
		// Assignments have not arguments so there are not references
		return false;
	}

	private Object value;
}
