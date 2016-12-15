package org.power_systems_modelica.psm.test.gui;

import java.io.File;
import java.nio.file.Paths;

import org.power_systems_modelica.psm.gui.utils.GuiFileChooser;

import javafx.stage.Stage;

public class GuiFileChooserFake extends GuiFileChooser {

	public GuiFileChooserFake(String filename) {
		
		super();
		this.filename = filename;
	}
	
	@Override
	public File showOpenDialog(Stage stage) {
		
		File initialDirectory = fileChooser.getInitialDirectory();
		return new File(Paths.get(initialDirectory.getAbsolutePath()).resolve(filename).toString());
	}

	@Override
	public File showSaveDialog(Stage stage) {
		
		File initialDirectory = fileChooser.getInitialDirectory();
		return new File(Paths.get(initialDirectory.getAbsolutePath()).resolve(filename).toString());
	}
	
	private String filename;
}
