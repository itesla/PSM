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

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelicaEquation implements Annotable
{
	public ModelicaEquation()
	{
		text = null;
		annotation = null;
	}

	public ModelicaEquation(String text)
	{
		this.text = text;
		this.annotation = null;
	}

	public ModelicaEquation(String text, Annotation annotation)
	{
		this.text = text;
		this.annotation = annotation;
	}

	public String getText()
	{
		return text;
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

	private final String	text;

	private Annotation		annotation;
}
