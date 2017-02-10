package org.power_systems_modelica.psm.gui.utils.fx;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GuiFileChooser {

	public GuiFileChooser() {
		
		fileChooser = new FileChooser();
	}
	
	public void setDetails(String initialDirectory, String description, String... extensions) {
			
        //Set extension filter
        FileChooser.ExtensionFilter extAllFilter = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(description, extensions);
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(extAllFilter);
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setSelectedExtensionFilter(extFilter);
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
