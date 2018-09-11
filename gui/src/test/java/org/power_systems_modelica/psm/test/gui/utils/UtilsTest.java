package org.power_systems_modelica.psm.test.gui.utils;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.ConvertedCase;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.Event;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.DsEngine;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.LoadflowEngine;
import org.power_systems_modelica.psm.gui.utils.Utils;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
public class UtilsTest
{

	@Test
	public void utils() throws IOException
	{
		
		StringBuilder b = PathUtils.loadFile(PathUtils.CONFIG.toString(), "gui.properties");
		assertTrue(b.length()>0);
		
		PathUtils.saveFile(PathUtils.DATA_TMP.toString(), "gui.properties", b);
		assertTrue(PathUtils.existsFile(PathUtils.DATA_TMP.toString(), "gui.properties"));
		
		Properties p = PathUtils.getGUIProperties();
		assertFalse(p.isEmpty());
		
		String pad = Utils.padString("test", 5);
		assertEquals("test ", pad);

		String replace = Utils.replaceLast("test tested", "test", "check");
		assertEquals("test checked", replace);

		String replace2 = Utils.replaceLast("test tested", "not", "check");
		assertEquals("test tested", replace2);

		LoadflowEngine le = Utils.getLoadflowEngine("loadflowHelmflow");
		assertEquals(LoadflowEngine.HELMFLOW, le);

		DsEngine dse = Utils.getDsEngine("OpenModelica");
		assertEquals(DsEngine.OPENMODELICA, dse);
		
		Case cs = new Case();
		cs.setLocation(PathUtils.DATA_TEST.resolve("ieee14").toString());
		Ddr ddr = new Ddr();
		ddr.setLocation(PathUtils.DATA_TEST.resolve("ieee14").resolve("ddr").toString());
		
		p = Utils.getConversionProperties(cs, ddr, le, true, dse);
		assertEquals(5, p.size());

		File file = PathUtils.DATA_TMP.resolve("conversion.properties").toFile();
    	OutputStream out = new FileOutputStream(file);
        p.store(out, "Conversion configuration file");
        out.close();

        Properties defaultProperties = new Properties();
        defaultProperties.setProperty("conversionPropertiesFile", file.getAbsolutePath());
        Path defaultFile = PathUtils.CONFIG.resolve("conversion.properties");
        out = Files.newOutputStream(defaultFile);
        defaultProperties.store(out, "Default conversion configuration file");
        out.close();

        p = PathUtils.loadDefaultConversionFile();
		assertFalse(p.isEmpty());
        
		ConvertedCase cvs = new ConvertedCase();
		cvs.setLocation(PathUtils.DATA_TEST.resolve("ieee14").toString());
		
		List<Event> events = new ArrayList<>();
		
		p = Utils.getSimulationProperties(cvs, events, dse, "1.0", "100", false);
		assertEquals(5, p.size());
		
		file = PathUtils.DATA_TMP.resolve("simulation.properties").toFile();
    	out = new FileOutputStream(file);
        p.store(out, "Simulation configuration file");
        out.close();

        defaultProperties = new Properties();
        defaultProperties.setProperty("simulationPropertiesFile", file.getAbsolutePath());
        defaultFile = PathUtils.CONFIG.resolve("simulation.properties");
        out = Files.newOutputStream(defaultFile);
        defaultProperties.store(out, "Default simulation configuration file");
        out.close();

        p = PathUtils.loadDefaultSimulationFile();
		assertFalse(p.isEmpty());

	}

}
