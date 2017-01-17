package org.power_systems_modelica.psm.gui.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

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

public class SimulationCheckVerifyDetailController implements MainChildrenController {

	@Override
	public void handleMainAction() {
		
		handleSimulateWorkflow(false);
	}

	@Override
	public void handleMenuAction(String action)
	{
		
		switch(action) {
		case "New":
			handleNewWorkflow();
			break;
		case "Verify":
			handleSimulateWorkflow(true);
			break;
		}
	}

	@Override
	public String getMainAction() {

		return "Simulate";
	}

	@Override
	public List<String> getMenuActions() {

		List<String> actions = new ArrayList();
		actions.add("New");
		if (isCheckDetail)
			actions.add("Verify");
		return actions;
	}

	@Override
	public List<String> getSummaryLabels() {
		
		List<String> labels = new ArrayList();
		labels.add("Case:");
		labels.add(caseLabel);
		labels.add("Created:");
		labels.add(createdLabel);
		if (isCheckDetail)
			labels.add("Check:");
		else
			labels.add("Verify:");
		labels.add(checkLabel);
		return labels;
	}
	
	@FXML
	private void initialize() {
	}

	private void handleNewWorkflow() {

		mainService.showSimulationView(null);
	}
	
	private void handleSimulateWorkflow(boolean isVerify) {

		try {
			ConvertedCase cs = mainService.getConvertedCase(catalogName, casePath);
			mainService.startSimulation(cs, events, dse, stopTime, false, isVerify);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setMainService(MainService mainService, Workflow w, boolean isCheckDetail) {

		this.mainService = mainService;
		this.isCheckDetail = isCheckDetail;

		if (isCheckDetail) {
			panel.setText("Simulation check detail");
		}
		else {
			panel.setText("Simulation verify detail");
		}
		
		checkLabel = "Label";
		for (TaskDefinition td : w.getConfiguration().getTaskDefinitions()) {

			if (td.getTaskClass().equals(ModelicaParserTask.class)) {
				String moInput = td.getTaskConfiguration().getParameter("source");
				
				try {
					BasicFileAttributes attr = Files.readAttributes(Paths.get(moInput), BasicFileAttributes.class);
					DateTime date = new DateTime(attr.lastModifiedTime().toMillis());
					
					createdLabel = date.toString("yyyy/MM/dd HH:mm:ss");
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
					caseLabel = catalogName + "\t" + c.getName();
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
	private DynamicTreeView<ProgressData> treeView;

	private String caseLabel;
	private String createdLabel;
	private String checkLabel;

	private String catalogName;
	private Path casePath;
	private ObservableList<Event> events = FXCollections.observableArrayList();
	private String stopTime;
	private DsEngine dse;
	
	private boolean isCheckDetail;

	private MainService mainService;

	private static final Logger LOG = LoggerFactory.getLogger(SimulationCheckVerifyDetailController.class);
}
