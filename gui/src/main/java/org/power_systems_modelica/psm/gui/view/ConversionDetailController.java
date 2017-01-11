package org.power_systems_modelica.psm.gui.view;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.ConvertedCase;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.utils.CodeEditor;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.ModelicaExporterTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
				
				String uri = td.getTaskConfiguration().getParameter("target");
				Path moFile = Paths.get(uri);
				pathLabel.setText(uri);
				
				StringBuilder moContent = new StringBuilder();
				try {
					moContent = PathUtils.loadFile(moFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				codeEditor.setCode(moContent);
			}
			
			if (td.getTaskClass().equals(StaticNetworkImporterTask.class)) {
				
				String uri = td.getTaskConfiguration().getParameter("source");
				
				Path casePath;
				if (uri.endsWith(".xml")) {
					Path path = Paths.get(uri);
					casePath = path.getParent();
				} else
					casePath = Paths.get(uri);
				
				Path catalogPath = casePath.getParent();

				try {
					Catalog catalog = mainService.getCatalog("cases", catalogPath);
					Case c = mainService.getCase(catalog.getName(), casePath);
					caseLabel.setText(catalog.getName() + "\t" + c.getName());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (td.getTaskClass().equals(ModelicaNetworkBuilderTask.class)) {
				
				String uri = td.getTaskConfiguration().getParameter("ddrLocation");
				Path ddrPath = Paths.get(uri).normalize();
				Path catalogPath = ddrPath.getParent().getParent();
				
				try {
					Catalog catalog = mainService.getCatalog("ddrs", catalogPath);
					Ddr ddr = mainService.getDdr(catalog.getName(), ddrPath);
					ddrLabel.setText(catalog.getName() + "\t" + ddr.getName());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void setConversionResult(Case c) {
		
		Path casePath = Paths.get(c.getLocation());
		Path catalogPath = casePath.getParent();
		Path convertedPath = casePath.resolve(c.getName() + ".mo");

		Catalog catalog;
		try {
			catalog = mainService.getCatalog("cases", catalogPath);
			ConvertedCase cc = mainService.getConvertedCase(catalog.getName(), casePath);
			
			Path ddrPath = Paths.get(cc.getDdrLocation());
			Ddr ddr = mainService.getDdr(catalog.getName(), ddrPath);
			
			pathLabel.setText(convertedPath.toString());
			caseLabel.setText(catalog.getName() + "\t" + c.getName());
			ddrLabel.setText(catalog.getName() + "\t" + ddr.getName());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		StringBuilder moContent = new StringBuilder();
		try {
			moContent = PathUtils.loadFile(convertedPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		codeEditor.setCode(moContent);
	}
	

	@FXML
	private TitledPane fileContentPane;
	
	@FXML
	private Label pathLabel;

	@FXML
	private Label caseLabel;

	@FXML
	private Label ddrLabel;
	
	@FXML
	private CodeEditor codeEditor;

	private MainService mainService;

}
