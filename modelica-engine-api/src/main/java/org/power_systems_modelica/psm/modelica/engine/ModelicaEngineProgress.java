package org.power_systems_modelica.psm.modelica.engine;

/*
 * #%L
 * Modelica Engine API
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.util.Observable;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelicaEngineProgress extends Observable
{

	public void updateProgress(String info)
	{
		setChanged();
		notifyObservers(info);
	}
}
