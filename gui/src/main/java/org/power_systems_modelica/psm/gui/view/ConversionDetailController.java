package org.power_systems_modelica.psm.gui.view;

import java.io.IOException;
import java.nio.file.Paths;

import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.utils.CodeEditor;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.ModelicaExporterTask;

import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;

public class ConversionDetailController {

	@FXML
	private void initialize() {

	}

	@FXML
	private void handleNewConversionEvent() {
		mainService.showConversionNewView(mainService.getConversion());
	}

	public void setMainService(MainService mainService) {
		this.mainService = mainService;
		
	}

	public void setWorkflow(Workflow w) {
		
		for (TaskDefinition td : w.getConfiguration().getTaskDefinitions()) {

			if (td.getTaskClass().equals(ModelicaExporterTask.class)) {
				String moFile = td.getTaskConfiguration().getParameter("target");
				StringBuilder moContent = new StringBuilder();
				try {
					moContent = PathUtils.loadFile(Paths.get(moFile));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				codeEditor.setCode(moContent);
			}
		}
	}
	
	public void setConversionResult(Case c) {
		
		StringBuilder moContent = new StringBuilder();
		try {
			moContent = PathUtils.loadFile(Paths.get(c.getLocation()).resolve(c.getName() + ".mo"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		codeEditor.setCode(moContent);
	}
	

	@FXML
	private TitledPane fileContentPane;
	@FXML
	private CodeEditor codeEditor;

	private MainService mainService;

}
