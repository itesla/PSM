package org.power_systems_modelica.psm.gui.view;

/*
 * #%L
 * Power Systems on Modelica GUI
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import org.power_systems_modelica.psm.gui.service.fx.MainService;
import org.power_systems_modelica.psm.workflow.Workflow;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
public interface SimulationResultDetailController
{
	void setWorkflow(Workflow w, Object... objects);

	void setMainService(MainService mainService);
}
