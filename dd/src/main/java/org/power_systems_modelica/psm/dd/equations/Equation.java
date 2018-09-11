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

import org.power_systems_modelica.psm.dd.Stage;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public abstract class Equation
{
	public abstract String writeIn(Context<?> context);

	public Equation()
	{
		this.stage = Stage.SIMULATION;
	}

	public Equation(Stage stage)
	{
		this.stage = stage;
	}

	public void setStage(Stage stage)
	{
		this.stage = stage;
	}

	public Stage getStage()
	{
		return stage;
	}

	private Stage stage;
}
