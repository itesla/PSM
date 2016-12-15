package org.power_systems_modelica.psm.gui.utils;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GuiFileChooser {

	public GuiFileChooser() {
		
		fileChooser = new FileChooser();
	}
	
	public void setDetails(String initialDirectory, String description, String... extensions) {
			
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(description, extensions);
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File(initialDirectory));
		
	}
	
	public File showOpenDialog(Stage stage) {
		
		File selectedFile = fileChooser.showOpenDialog(stage);
		return selectedFile;
	}

	public File showSaveDialog(Stage stage) {
	    //Show save file dialog
	    File selectedFile = fileChooser.showSaveDialog(stage);
	    
	    return selectedFile;
	}
	
	public void setInitialFileName(String file) {

		fileChooser.setInitialFileName(file);
	}

	protected FileChooser fileChooser;
}
