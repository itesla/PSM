package org.power_systems_modelica.psm.test.gui;

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
import java.nio.file.Paths;

import org.power_systems_modelica.psm.gui.utils.fx.GuiFileChooser;

import javafx.stage.Stage;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
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
