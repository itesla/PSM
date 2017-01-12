package org.power_systems_modelica.psm.gui.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

import org.joda.time.DateTime;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.ConvertedCase;
import org.power_systems_modelica.psm.gui.model.Event;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.DsEngine;
import org.power_systems_modelica.psm.gui.utils.DynamicTreeView;
import org.power_systems_modelica.psm.gui.utils.ProgressData;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.ModelicaEventAdderTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaParserTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaSimulatorTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;

public class SimulationCheckVerifyDetailController {

	@FXML
	private void initialize() {
	}

	@FXML
	private void handleNewWorkflow() {
		LOG.debug("handleNewWorkflow");
		mainService.showSimulationView(null);
	}
	
	@FXML
	private void handleSimulateWorkflow() {
		LOG.debug("handleSimulateWorkflow");

		try {
			ConvertedCase cs = mainService.getConvertedCase(catalogName, casePath);
			mainService.startSimulation(cs, events, dse, stopTime, false, isCheckDetail);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setMainService(MainService mainService, Workflow w, boolean isCheckDetail) {
		this.mainService = mainService;
		this.isCheckDetail = isCheckDetail;

		if (isCheckDetail) {
			simButtonTop.setText("Verify");
			simButtonBottom.setText("Verify");
			panel.setText("Simulation check detail");
		}
		else {
			simButtonTop.setText("Simulate");
			simButtonBottom.setText("Simulate");
			panel.setText("Simulation verify detail");
		}
		
		for (TaskDefinition td : w.getConfiguration().getTaskDefinitions()) {

			if (td.getTaskClass().equals(ModelicaParserTask.class)) {
				String moInput = td.getTaskConfiguration().getParameter("source");
				
				try {
					BasicFileAttributes attr = Files.readAttributes(Paths.get(moInput), BasicFileAttributes.class);
					DateTime date = new DateTime(attr.creationTime().toMillis());
					
					createdLabel.setText(date.toString("yyyy/MM/dd HH:mm:ss"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (td.getTaskClass().equals(StaticNetworkImporterTask.class)) {
				
				String uri = td.getTaskConfiguration().getParameter("source");
				
				if (uri.endsWith(".xml")) {
					Path path = Paths.get(uri);
					casePath = path.getParent();
				} else
					casePath = Paths.get(uri);
				
				Path catalogPath = casePath.getParent();

				try {
					Catalog catalog = mainService.getCatalog("cases", catalogPath);
					catalogName = catalog.getName();
					
					Case c = mainService.getCase(catalog.getName(), casePath);
					caseLabel.setText(catalogName + "\t" + c.getName());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (td.getTaskClass().equals(ModelicaEventAdderTask.class)) {
				
				events.clear();
				String[] evs = td.getTaskConfiguration().getParameter("events").split("\n");
				for (String event : evs) {

					Event e = new Event();
					e.fromString(event);
					events.add(e);
				}
			}

			if (td.getTaskClass().equals(ModelicaSimulatorTask.class)) {
				stopTime = td.getTaskConfiguration().getParameter("stopTime");

				dse = Utils.getDsEngine(td.getTaskId());
			}
		}
	}

	@FXML
	private TitledPane panel;

	@FXML
	private Label caseLabel;

	@FXML
	private Label createdLabel;

	@FXML
	private Label checkLabel;
	
	@FXML
	private Button simButtonTop;

	@FXML
	private Button simButtonBottom;

	@FXML
	private DynamicTreeView<ProgressData> treeView;

	private String catalogName;
	private Path casePath;
	private ObservableList<Event> events = FXCollections.observableArrayList();
	private String stopTime;
	private DsEngine dse;
	
	private boolean isCheckDetail;

	private MainService mainService;

	private static final Logger LOG = LoggerFactory.getLogger(SimulationCheckVerifyDetailController.class);
}
