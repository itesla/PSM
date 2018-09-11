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

import java.util.List;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public abstract class BaseModelicaDeclaration implements Annotable
{
	public BaseModelicaDeclaration(
			String type,
			String id,
			boolean isAssignment,
			boolean isParameter,
			Annotation annotation)
	{
		this.type = type;
		this.id = id;
		this.isAssignment = isAssignment;
		this.isParameter = isParameter;
		this.annotation = annotation;
	}

	public abstract Object getValue();
	
	public abstract List<ModelicaArgument> getArguments();
	
	public abstract void replaceArguments(List<ModelicaArgument> args1);

	public abstract boolean containsAnyReference();
	
	public String getType()
	{
		return type;
	}

	public String getId()
	{
		return id;
	}

	public boolean isParameter()
	{
		return isParameter;
	}

	public boolean isAssignment()
	{
		return isAssignment;
	}

	@Override
	public Annotation getAnnotation()
	{
		return annotation;
	}

	@Override
	public void setAnnotation(Annotation annotation)
	{
		this.annotation = annotation;
	}

	public void setOrigin(String origin)
	{
		this.origin = origin;
	}

	public String getOrigin()
	{
		return origin;
	}

	private final String			type;
	private final String			id;
	private final boolean			isAssignment;
	private final boolean			isParameter;
	
	private Annotation				annotation;

	private String					origin;
}
