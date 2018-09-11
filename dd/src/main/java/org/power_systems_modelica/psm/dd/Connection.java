package org.power_systems_modelica.psm.dd;

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
public class Connection
{
	public Connection(String id1, String var1, String id2, String var2)
	{
		this.id1 = id1;
		this.var1 = var1;
		this.id2 = id2;
		this.var2 = var2;
	}

	public String getId1()
	{
		return id1;
	}

	public String getVar1()
	{
		return var1;
	}

	public String getId2()
	{
		return id2;
	}

	public String getVar2()
	{
		return var2;
	}

	private final String	id1;
	private final String	var1;
	private final String	id2;
	private final String	var2;
}
