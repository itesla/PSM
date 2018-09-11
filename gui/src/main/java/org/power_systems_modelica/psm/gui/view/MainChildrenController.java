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

import java.util.List;

import org.power_systems_modelica.psm.gui.model.SummaryLabel;
import org.power_systems_modelica.psm.gui.service.fx.MainService;
import org.power_systems_modelica.psm.gui.utils.fx.GuiFileChooser;
import org.power_systems_modelica.psm.workflow.Workflow;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
public class MainChildrenController
{

	public void handleMainAction()
	{
	}

	public void handleMenuAction(String action)
	{
	}

	public String getMainAction()
	{
		return null;
	}

	public List<String> getMenuActions()
	{
		return null;
	}

	public List<SummaryLabel> getSummaryLabels()
	{
		return null;
	}

	public void setMainService(MainService mainService)
	{
		this.mainService = mainService;
	}

	public void setFileChooser(GuiFileChooser fileChooser)
	{
	}

	public void setDefaultInit()
	{
	}

	public void setWorkflow(Workflow w, Object... objects)
	{
	}

	public ObservableValue<? extends Boolean> disableMainAction()
	{
		return new SimpleBooleanProperty(false);
	}

	public ObservableValue<? extends Boolean> disableBackground()
	{
		return new SimpleBooleanProperty(false);
	}

	public Button getDefaultEnterButton()
	{
		return null;
	}

	protected MainService mainService;
}
