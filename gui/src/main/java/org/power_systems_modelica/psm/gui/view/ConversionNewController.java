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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.ddr.dyd.Diagnostics;
import org.power_systems_modelica.psm.gui.MainApp.WorkflowType;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.service.CaseService;
import org.power_systems_modelica.psm.gui.service.CatalogService;
import org.power_systems_modelica.psm.gui.service.DdrService;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.DsEngine;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.LoadflowEngine;
import org.power_systems_modelica.psm.gui.service.fx.MainService;
import org.power_systems_modelica.psm.gui.service.fx.TaskService;
import org.power_systems_modelica.psm.gui.service.fx.WorkflowService;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.gui.utils.fx.GuiFileChooser;
import org.power_systems_modelica.psm.gui.utils.fx.PathUtilsFX;
import org.power_systems_modelica.psm.gui.utils.fx.UtilsFX;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;
import org.power_systems_modelica.psm.workflow.psm.LoadFlowTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
public class ConversionNewController extends MainChildrenController
{

	@Override
	public void handleMainAction()
	{
		handleStartWorkflow();
	}

	@Override
	public void handleMenuAction(String action)
	{
		switch (action)
		{
		case "Load":
			handleLoadWorkflow();
			break;
		case "Save":
			handleSaveWorkflow();
			break;
		case "Clean":
			handleCleanWorkflow();
			break;
		case "Check":
			handleCheckWorkflow();
			break;
		}
	}

	@Override
	public String getMainAction()
	{
		return "Start";
	}

	@Override
	public List<String> getMenuActions()
	{
		List<String> actions = new ArrayList<String>();
		actions.add("Load");
		actions.add("Save");
		actions.add("Clean");
		actions.add("separator");
		actions.add("Check");
		return actions;
	}

	public void setCase(Case c)
	{

		UtilsFX.resolveCasePath(c.getLocation(), catalogCaseSource, caseSource);
		setDdr(c);
	}

	@Override
	public void setWorkflow(Workflow w, Object... objects)
	{

		handleCleanWorkflow();

		for (TaskDefinition td : w.getConfiguration().getTaskDefinitions())
		{

			if (td.getTaskClass().equals(StaticNetworkImporterTask.class))
			{
				String casePath = td.getTaskConfiguration().getParameter("source");
				UtilsFX.resolveCasePath(casePath, catalogCaseSource, caseSource);
			}

			if (td.getTaskClass().equals(ModelicaNetworkBuilderTask.class))
			{
				String ddrPath = td.getTaskConfiguration().getParameter("ddrLocation");
				UtilsFX.resolveDdrPath(ddrPath, catalogDdrSource, ddrSource);

				Boolean onlyMainConnectedComponent = td.getTaskConfiguration()
						.getBoolean("onlyMainConnectedComponent");
				mainConnectedComponent.setSelected(onlyMainConnectedComponent);
			}

			if (td.getTaskClass().equals(LoadFlowTask.class))
				loadflowEngine.getSelectionModel().select(Utils.getLoadflowEngine(td.getTaskId()));

			if (td.getTaskClass().equals(ModelicaNetworkBuilderTask.class))
			{
				String simulationEngine = td.getTaskConfiguration().getParameter("modelicaEngine");
				if (simulationEngine != null)
					dsEngine.getSelectionModel().select(Utils.getDsEngine(simulationEngine));
			}
		}
	}

	@Override
	public void setMainService(MainService mainService)
	{
		super.setMainService(mainService);

		catalogCaseSource
				.setItems(FXCollections.observableArrayList(CatalogService.getCatalogs("cases")));
		catalogDdrSource
				.setItems(FXCollections.observableArrayList(CatalogService.getCatalogs("ddrs")));

		loadflowEngine
				.setItems(FXCollections
						.observableArrayList(WorkflowServiceConfiguration.getLoadflowEngines()));

		dsEngine.setItems(
				FXCollections.observableArrayList(WorkflowServiceConfiguration.getDsEngines()));
	}

	@Override
	public void setFileChooser(GuiFileChooser fileChooser)
	{
		this.fileChooser = fileChooser;
	}

	@Override
	public void setDefaultInit()
	{
		handleCleanWorkflow();
		try
		{
			Properties workflowProperties = PathUtils.loadDefaultConversionFile();
			loadWorkflow(workflowProperties);
		}
		catch (IOException e)
		{
		}
	}

	@FXML
	private void initialize()
	{

		Properties p = PathUtils.getGUIProperties();
		LE = Optional.ofNullable(p.getProperty("conversion.loadflow.engine"))
				.orElse("loadflowHelmflow");
		OMCC = Optional
				.ofNullable(p.getProperty("conversion.modelicaNetwork.onlyMainConnectedComponent"))
				.orElse("true");
		FMIE = Optional.ofNullable(p.getProperty("conversion.fullModelInitialization.engine"))
				.orElse("OpenModelica");

		catalogCaseSource.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Catalog>()
				{

					@Override
					public void changed(ObservableValue<? extends Catalog> observable,
							Catalog oldValue, Catalog newValue)
					{
						if (newValue != null)
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
					}

				});

		caseSource.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Case>()
		{

			@Override
			public void changed(ObservableValue<? extends Case> observable, Case oldValue,
					Case newValue)
			{
				if (newValue != null)
					setDdr(newValue);
			}
		});

		catalogDdrSource.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Catalog>()
				{

					@Override
					public void changed(ObservableValue<? extends Catalog> observable,
							Catalog oldValue, Catalog newValue)
					{
						if (newValue != null)
						{
							ddrSource.setItems(FXCollections
									.observableArrayList(DdrService.getDdrs(newValue.getName())));
							ddrSource.getItems().sort(new Comparator<Ddr>()
							{

								@Override
								public int compare(Ddr d1, Ddr d2)
								{
									return d1.getName().compareToIgnoreCase(d2.getName());
								}

							});
						}
					}

				});

	}

	private void handleLoadWorkflow()
	{

		handleCleanWorkflow();
		try
		{
			Properties workflowProperties = PathUtilsFX.loadConversionFile(fileChooser,
					mainService.getPrimaryStage(),
					System.getProperty("user.home"));
			loadWorkflow(workflowProperties);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void loadWorkflow(Properties workflowProperties)
	{

		if (workflowProperties.containsKey("casePath"))
		{
			String casePath = workflowProperties.getProperty("casePath");
			UtilsFX.resolveCasePath(casePath, catalogCaseSource, caseSource);
		}

		if (workflowProperties.containsKey("ddrPath"))
		{
			String ddrPath = workflowProperties.getProperty("ddrPath");
			UtilsFX.resolveDdrPath(ddrPath, catalogDdrSource, ddrSource);
		}

		if (workflowProperties.containsKey("loadflowEngine"))
		{
			String le = workflowProperties.getProperty("loadflowEngine");
			loadflowEngine.getSelectionModel().select(Utils.getLoadflowEngine(le));
		}

		if (workflowProperties.containsKey("onlyMainConnectedComponent"))
		{
			Boolean onlyMainConnectedComponent = Boolean
					.valueOf(workflowProperties.getProperty("onlyMainConnectedComponent"));
			mainConnectedComponent.setSelected(onlyMainConnectedComponent);
		}

		if (workflowProperties.containsKey("fullModelInitializationEngine"))
		{
			String dse = workflowProperties.getProperty("fullModelInitializationEngine");
			dsEngine.getSelectionModel().select(Utils.getDsEngine(dse));
		}
	}

	private void handleSaveWorkflow()
	{

		Case cs = caseSource.getSelectionModel().getSelectedItem();
		Ddr ddr = ddrSource.getSelectionModel().getSelectedItem();
		LoadflowEngine le = loadflowEngine.getSelectionModel().getSelectedItem();
		boolean onlyMainConnectedComponent = mainConnectedComponent.isSelected();
		DsEngine dse = dsEngine.getSelectionModel().getSelectedItem();

		Properties workflowProperties;
		try
		{
			workflowProperties = Utils.getConversionProperties(cs, ddr, le,
					onlyMainConnectedComponent, dse);
			PathUtilsFX.saveConversionFile(fileChooser, mainService.getPrimaryStage(),
					System.getProperty("user.home"), workflowProperties);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void handleCleanWorkflow()
	{
		caseSource.getSelectionModel().clearSelection();
		catalogCaseSource.getSelectionModel().clearSelection();
		ddrSource.getSelectionModel().clearSelection();
		catalogDdrSource.getSelectionModel().clearSelection();

		loadflowEngine.getSelectionModel().select(Utils.getLoadflowEngine(LE));

		mainConnectedComponent.setSelected(Boolean.parseBoolean(OMCC));

		dsEngine.getSelectionModel().select(Utils.getDsEngine(FMIE));
	}

	private void handleCheckWorkflow()
	{

		Case cs = caseSource.getSelectionModel().getSelectedItem();
		if (cs == null)
		{
			UtilsFX.showWarning("Warning", "Select a case");
			return;
		}
		Ddr ddr = ddrSource.getSelectionModel().getSelectedItem();
		if (ddr == null)
		{
			UtilsFX.showWarning("Warning", "Select a DDR");
			return;
		}

		boolean onlyMainConnectedComponent = mainConnectedComponent.isSelected();
		DsEngine dse = dsEngine.getSelectionModel().getSelectedItem();

		startConversion(cs, ddr, LoadflowEngine.NONE, onlyMainConnectedComponent, dse,
				true);
	}

	private void handleStartWorkflow()
	{
		LOG.debug("handleStartWorkflow");

		Case cs = caseSource.getSelectionModel().getSelectedItem();
		if (cs == null)
		{
			UtilsFX.showWarning("Warning", "Select a case");
			return;
		}
		Ddr ddr = ddrSource.getSelectionModel().getSelectedItem();
		if (ddr == null)
		{
			UtilsFX.showWarning("Warning", "Select a DDR");
			return;
		}

		LoadflowEngine le = loadflowEngine.getSelectionModel().getSelectedItem();
		if (le == null)
		{
			UtilsFX.showWarning("Warning", "Select a Loadflow engine");
			return;
		}

		boolean onlyMainConnectedComponent = mainConnectedComponent.isSelected();

		DsEngine dse = dsEngine.getSelectionModel().getSelectedItem();
		if (dse == null)
		{
			UtilsFX.showWarning("Warning", "Select a full model initialization engine");
			return;
		}

		startConversion(cs, ddr, le, onlyMainConnectedComponent, dse, false);
	}

	private void startConversion(Case cs, Ddr ddr, LoadflowEngine le,
			boolean onlyMainConnectedComponent, DsEngine dse, boolean onlyCheck)
	{

		try
		{
			Workflow w = WorkflowServiceConfiguration.createConversion(cs, ddr, le,
					onlyMainConnectedComponent, dse, onlyCheck);

			Diagnostics diagnostics = DdrService.check(ddr.getLocation());

			StringBuilder ddrContent = Utils.getCheckDdrResult(diagnostics);
			if (ddrContent.length() != 0)
			{
				conversionFinish(w, onlyCheck, diagnostics);
				return;
			}

			Task<?> task = TaskService.createTask(w,
					() -> conversionFinish(w, onlyCheck, diagnostics));
			mainService.setConversionTask(task);
			mainService.getMainApp().showWorkflowStatusView(mainService, w,
					WorkflowType.CONVERSION);
			TaskService.startTask(task);
			if (!onlyCheck)
			{
				CaseService.saveConvertedCaseProperties(cs.getLocation(), ddr.getLocation(),
						le.toString(),
						onlyMainConnectedComponent, dse.toString());
			}
		}
		catch (WorkflowCreationException e)
		{
			e.printStackTrace();
		}
	}

	private void conversionFinish(Workflow w, boolean onlyCheck, Diagnostics diagnostics)
	{
		if ((WorkflowService) mainService.getConversionTask() != null
				&& ((WorkflowService) mainService.getConversionTask()).isCancelled())
			mainService.getMainApp().showConversionNewView(w);
		else
			mainService.getMainApp().showConversionDetailView(mainService, true,
					null, onlyCheck, diagnostics);
	}

	private void setDdr(Case c)
	{
		String ddrLocation = CaseService.getDefaultDdrLocation(c);
		if (ddrLocation != null)
		{
			UtilsFX.resolveDdrPath(ddrLocation, catalogDdrSource, ddrSource);
		}
	}

	@FXML
	private ComboBox<Catalog>			catalogCaseSource;
	@FXML
	private ComboBox<Case>				caseSource;
	@FXML
	private ComboBox<Catalog>			catalogDdrSource;
	@FXML
	private ComboBox<Ddr>				ddrSource;

	@FXML
	private ComboBox<LoadflowEngine>	loadflowEngine;

	@FXML
	private CheckBox					mainConnectedComponent;

	@FXML
	private ComboBox<DsEngine>			dsEngine;

	private GuiFileChooser				fileChooser;

	private String						LE;
	private String						OMCC;
	private String						FMIE;

	private static final Logger			LOG	= LoggerFactory
			.getLogger(ConversionNewController.class);

}
