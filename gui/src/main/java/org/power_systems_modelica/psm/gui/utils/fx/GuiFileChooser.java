package org.power_systems_modelica.psm.gui.utils.fx;

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

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
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
