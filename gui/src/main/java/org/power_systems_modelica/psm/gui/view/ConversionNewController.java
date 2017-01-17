package org.power_systems_modelica.psm.gui.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.LoadflowEngine;
import org.power_systems_modelica.psm.gui.utils.GuiFileChooser;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.LoadFlowTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

public class ConversionNewController implements MainChildrenController {

	@Override
	public void handleMainAction() {
		
		handleStartWorkflow();
	}

	@Override
	public void handleMenuAction(String action)
	{
		switch(action) {
		case "Load":
			handleLoadWorkflow();
			break;
		case "Save":
			handleSaveWorkflow();
			break;
		case "Clean":
			handleCleanWorkflow();
			break;
		}
	}

	@Override
	public String getMainAction() {
		
		return "Start";
	}

	@Override
	public List<String> getMenuActions() {
	
		List<String> actions = new ArrayList();
		actions.add("Load");
		actions.add("Save");
		actions.add("Clean");
		return actions;
	}

	@Override
	public List<String> getSummaryLabels() {
		
		return null;
	}

	@FXML
	private void initialize() {

		catalogCaseSource.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Catalog>() {

			@Override
			public void changed(ObservableValue<? extends Catalog> observable, Catalog oldValue, Catalog newValue) {
				if (newValue != null)
					caseSource.setItems(mainService.getCases(newValue.getName()));
			}

		});

		caseSource.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Case>() {

			@Override
			public void changed(ObservableValue<? extends Case> observable, Case oldValue, Case newValue) {
				if (newValue != null)
					setDdr(newValue);
			}
		});

		catalogDdrSource.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Catalog>() {

			@Override
			public void changed(ObservableValue<? extends Catalog> observable, Catalog oldValue, Catalog newValue) {
				if (newValue != null)
					ddrSource.setItems(mainService.getDdrs(newValue.getName()));
			}

		});

	}
	
	private void handleLoadWorkflow() {

		handleCleanWorkflow();
		try {
			Properties workflowProperties = PathUtils.loadConversionFile(fileChooser, mainService.getPrimaryStage(),
					System.getProperty("user.home"));
			loadWorkflow(workflowProperties);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loadWorkflow(Properties workflowProperties) {

		if (workflowProperties.containsKey("casePath")) {
			String casePath = workflowProperties.getProperty("casePath");
			Utils.resolveCasePath(casePath, catalogCaseSource, caseSource);
		}

		if (workflowProperties.containsKey("ddrPath")) {
			String ddrPath = workflowProperties.getProperty("ddrPath");
			Utils.resolveDdrPath(ddrPath, catalogDdrSource, ddrSource);
		}

		if (workflowProperties.containsKey("loadflowEngine")) {
			String le = workflowProperties.getProperty("loadflowEngine");
			loadflowEngine.getSelectionModel().select(Utils.getLoadflowEngine(le));
		}

		if (workflowProperties.containsKey("onlyMainConnectedComponent")) {
			Boolean onlyMainConnectedComponent = Boolean
					.valueOf(workflowProperties.getProperty("onlyMainConnectedComponent"));
			mainConnectedComponent.setSelected(onlyMainConnectedComponent);
		}
	}

	private void handleSaveWorkflow() {

		Case cs = caseSource.getSelectionModel().getSelectedItem();
		Ddr ddr = ddrSource.getSelectionModel().getSelectedItem();
		LoadflowEngine le = loadflowEngine.getSelectionModel().getSelectedItem();
		boolean onlyMainConnectedComponent = mainConnectedComponent.isSelected();

		Properties workflowProperties;
		try {
			workflowProperties = Utils.getConversionProperties(cs, ddr, le, onlyMainConnectedComponent);
			PathUtils.saveConversionFile(fileChooser, mainService.getPrimaryStage(), System.getProperty("user.home"), workflowProperties);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void handleCleanWorkflow() {
		caseSource.getSelectionModel().clearSelection();
		catalogCaseSource.getSelectionModel().clearSelection();
		ddrSource.getSelectionModel().clearSelection();
		catalogDdrSource.getSelectionModel().clearSelection();

		loadflowEngine.getSelectionModel().select(LoadflowEngine.NONE);

		mainConnectedComponent.setSelected(MAINCONNECTEDCOMPONENTDEFAULT);
	}

	private void handleStartWorkflow() {
		LOG.debug("handleStartWorkflow");

		Case cs = caseSource.getSelectionModel().getSelectedItem();
		if (cs == null) {
			Utils.showWarning("Warning", "Select a case");
			return;
		}
		Ddr ddr = ddrSource.getSelectionModel().getSelectedItem();
		if (ddr == null) {
			Utils.showWarning("Warning", "Select a DDR");
			return;
		}

		LoadflowEngine le = loadflowEngine.getSelectionModel().getSelectedItem();
		if (le == null) {
			Utils.showWarning("Warning", "Select a Loadflow engine");
			return;
		}

		boolean onlyMainConnectedComponent = mainConnectedComponent.isSelected();

		mainService.startConversion(cs, ddr, le, onlyMainConnectedComponent);
	}

	public void setCase(Case c) {

		Utils.resolveCasePath(c.getLocation(), catalogCaseSource, caseSource);
		setDdr(c);
	}

	private void setDdr(Case c) {
		String ddrLocation = mainService.getDefaultDdrLocation(c);
		if (ddrLocation != null) {
			Utils.resolveDdrPath(ddrLocation, catalogDdrSource, ddrSource);
		}
	}

	public void setWorkflow(Workflow w) {

		handleCleanWorkflow();

		for (TaskDefinition td : w.getConfiguration().getTaskDefinitions()) {

			if (td.getTaskClass().equals(StaticNetworkImporterTask.class)) {
				String casePath = td.getTaskConfiguration().getParameter("source");
				Utils.resolveCasePath(casePath, catalogCaseSource, caseSource);
			}

			if (td.getTaskClass().equals(ModelicaNetworkBuilderTask.class)) {
				String ddrPath = td.getTaskConfiguration().getParameter("ddrLocation");
				Utils.resolveDdrPath(ddrPath, catalogDdrSource, ddrSource);

				Boolean onlyMainConnectedComponent = td.getTaskConfiguration().getBoolean("onlyMainConnectedComponent");
				mainConnectedComponent.setSelected(onlyMainConnectedComponent);
			}

			if (td.getTaskClass().equals(LoadFlowTask.class))
				loadflowEngine.getSelectionModel().select(Utils.getLoadflowEngine(td.getTaskId()));

		}
	}

	public void setMainService(MainService mainService) {
		this.mainService = mainService;

		catalogCaseSource.setItems(mainService.getCatalogs("cases"));
		catalogDdrSource.setItems(mainService.getCatalogs("ddrs"));

		loadflowEngine.setItems(mainService.getLoadflowEngines());
		loadflowEngine.getSelectionModel().select(LoadflowEngine.NONE);

	}
	
	public void setFileChooser(GuiFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}

	public void setDefaultInit() {
		handleCleanWorkflow();
		try {
			Properties workflowProperties = PathUtils.loadDefaultConversionFile();
			loadWorkflow(workflowProperties);
		} catch (IOException e) {
		}
	}

	@FXML
	private ComboBox<Catalog> catalogCaseSource;
	@FXML
	private ComboBox<Case> caseSource;
	@FXML
	private ComboBox<Catalog> catalogDdrSource;
	@FXML
	private ComboBox<Ddr> ddrSource;

	@FXML
	private ComboBox<LoadflowEngine> loadflowEngine;

	@FXML
	private CheckBox mainConnectedComponent;

	private GuiFileChooser fileChooser;
	private MainService mainService;

	private static final Boolean MAINCONNECTEDCOMPONENTDEFAULT = new Boolean(true);
	private static final Logger LOG = LoggerFactory.getLogger(ConversionNewController.class);
}
