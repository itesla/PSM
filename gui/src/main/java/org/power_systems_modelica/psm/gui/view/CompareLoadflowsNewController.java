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

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.gui.MainApp.WorkflowType;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.service.CaseService;
import org.power_systems_modelica.psm.gui.service.CatalogService;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.LoadflowEngine;
import org.power_systems_modelica.psm.gui.service.fx.MainService;
import org.power_systems_modelica.psm.gui.service.fx.TaskService;
import org.power_systems_modelica.psm.gui.service.fx.WorkflowService;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.gui.utils.fx.UtilsFX;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
public class CompareLoadflowsNewController extends MainChildrenController
{
	@Override
	public void handleMainAction()
	{
		handleStartWorkflow();
	}

	@Override
	public String getMainAction()
	{
		return "Start";
	}

	public void setCase(Case c)
	{
		List<Catalog> catalogs = CatalogService.getCatalogs("cases");

		FilteredList<Catalog> filteredCatalogs = new FilteredList<Catalog>(
				FXCollections.observableArrayList(catalogs),
				catalog -> c.getLocation().contains(catalog.getLocation()));

		filteredCatalogs.forEach(catalog -> {
			catalogSource.getSelectionModel().select(catalog);
		});

		caseSource.getSelectionModel().select(c);
	}

	@Override
	public void setMainService(MainService mainService)
	{
		super.setMainService(mainService);

		catalogSource
				.setItems(FXCollections.observableArrayList(CatalogService.getCatalogs("cases")));

		loadflowEngine
				.setItems(FXCollections
						.observableArrayList(WorkflowServiceConfiguration.getLoadflowComparisionEngines()));
		
		loadflowEngine.getSelectionModel().select(LoadflowEngine.HELMFLOW);
		
	}

	@FXML
	private void initialize()
	{
		Properties p = PathUtils.getGUIProperties();
		EGRL = Optional.ofNullable(
				p.getProperty("compareLoadflows.loadflow.enforceGeneratorsReactiveLimits"))
				.orElse("true");
		UHRAIS = Optional
				.ofNullable(p.getProperty("compareLoadflows.HELMflow.useHadesResultsAsInputState"))
				.orElse("false");
		DISABLEHELMFLOWFROMHADESRESULT = Boolean.valueOf(Optional
				.ofNullable(p.getProperty("compareLoadflows.disableHelmflowFromHadesResults"))
				.orElse("true"));

		if (DISABLEHELMFLOWFROMHADESRESULT)
			helmflowFromHadesResults.setVisible(false);

		enforceGeneratorsReactiveLimits.setSelected(Boolean.parseBoolean(EGRL));
		helmflowFromHadesResults.setSelected(Boolean.parseBoolean(UHRAIS));

		catalogSource.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Catalog>()
				{
					@Override
					public void changed(ObservableValue<? extends Catalog> observable,
							Catalog oldValue, Catalog newValue)
					{
						caseSource.setItems(FXCollections
								.observableArrayList(CaseService.getCases(newValue.getName())));
						caseSource.getItems().sort(new Comparator<Case>()
						{

							@Override
							public int compare(Case d1, Case d2)
							{
								return d1.getName().compareToIgnoreCase(d2.getName());
							}

						});
					}
				});
	}

	private void handleStartWorkflow()
	{
		LOG.debug("handleStartWorkflow");
		Case cs = (Case) caseSource.getSelectionModel().getSelectedItem();
		if (cs == null)
		{
			UtilsFX.showWarning("Warning", "Select a case");
			return;
		}
		LoadflowEngine le = loadflowEngine.getSelectionModel().getSelectedItem();
		if (le == null)
		{
			UtilsFX.showWarning("Warning", "Select a Loadflow engine");
			return;
		}
		boolean generatorsReactiveLimits = enforceGeneratorsReactiveLimits.isSelected();
		boolean helmflowFromHadesResultsValue = helmflowFromHadesResults.isSelected();
		startCompareLoadflows(cs, le, generatorsReactiveLimits,
				helmflowFromHadesResultsValue);
	}

	private void startCompareLoadflows(Case cs, LoadflowEngine le, boolean generatorsReactiveLimits,
			boolean helmflowFromHadesResults)
	{

		try
		{
			Workflow w = WorkflowServiceConfiguration.createCompareLoadflows(cs, le,
					generatorsReactiveLimits, helmflowFromHadesResults);
			Task<?> task = TaskService.createTask(w,
					() -> compareLoadflowsFinish());
			mainService.setCompareLoadflowTask(task);
			mainService.getMainApp().showWorkflowStatusView(mainService, w,
					WorkflowType.COMPARELOADFLOW);
			TaskService.startTask(task);
		}
		catch (WorkflowCreationException e)
		{
			e.printStackTrace();
		}
	}

	private void compareLoadflowsFinish()
	{
		if (((WorkflowService) mainService.getCompareLoadflowTask()).isCancelled())
			mainService.getMainApp().showCompareLoadflowsView(mainService, null);
		else
			mainService.getMainApp().showCompareLoadflowsDetailView(mainService);
	}

	@FXML
	private ComboBox<Catalog>			catalogSource;
	@FXML
	private ComboBox<Case>				caseSource;
	@FXML
	private ComboBox<LoadflowEngine>	loadflowEngine;
	@FXML
	private CheckBox					enforceGeneratorsReactiveLimits;
	@FXML
	private CheckBox					helmflowFromHadesResults;

	private String						EGRL;
	private String						UHRAIS;

	private static final Logger			LOG	= LoggerFactory
			.getLogger(CompareLoadflowsNewController.class);

	private Boolean						DISABLEHELMFLOWFROMHADESRESULT;
}
