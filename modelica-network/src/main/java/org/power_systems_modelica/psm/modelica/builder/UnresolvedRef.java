package org.power_systems_modelica.psm.modelica.builder;

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
public class UnresolvedRef
{
	// TODO Now kind is only interconnection,
	// but it could be interconnection or parameter,
	// create an enumeration when we re-factor this class to support unresolved parameters
	public UnresolvedRef(String kind, String source, String target, String name)
	{
		this.kind = kind;
		this.source = source;
		this.target = target;
		this.name = name;
	}

	public String getKind()
	{
		return kind;
	}

	public String getSource()
	{
		return source;
	}

	public String getTarget()
	{
		return target;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return String.format("%s reference not resolved: %s.%s in %s", kind, target, name, source);
	}

	private final String	kind;
	private final String	source;
	private final String	target;
	private final String	name;
}
