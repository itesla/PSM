package org.power_systems_modelica.psm.workflow.test.psm;

/*
 * #%L
 * Power systems on Modelica workflow
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.power_systems_modelica.psm.commons.test.TestUtil.DATA;
import static org.power_systems_modelica.psm.commons.test.TestUtil.DATA_TMP;
import static org.power_systems_modelica.psm.workflow.ProcessState.SUCCESS;
import static org.power_systems_modelica.psm.workflow.Workflow.TC;
import static org.power_systems_modelica.psm.workflow.Workflow.TD;
import static org.power_systems_modelica.psm.workflow.Workflow.WF;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;
import org.power_systems_modelica.psm.modelica.parser.test.ModelicaParserReferenceCases;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.ModelicaExporterTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaParserTask;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelicaParserReferenceCasesAsWorkflow extends ModelicaParserReferenceCases
{
	@Override
	protected void parse(String catalog, String foldername, String moname) throws Exception
	{
		Path folder = DATA.resolve(catalog).resolve(foldername);
		String moInput = folder.resolve(moname).toString();
		String moOutput = DATA_TMP.resolve("parsed_" + moname).toString();

		Workflow wf = WF(
				TD(ModelicaParserTask.class, "moparser0",
						TC("source", moInput, "modelicaDocument", "mo")),
				TD(ModelicaExporterTask.class, "exporter0",
						TC("target", moOutput)));
		wf.start();

		assertEquals(SUCCESS, wf.getTaskStates().get(0).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(1).state);
		assertEquals(SUCCESS, wf.getState());

		Path expected = folder.resolve(moInput);
		Path actual = Paths.get(moOutput);

		//Check if genberated .mo and expected .mo are equals checking declarations and equations
		ModelicaDocument moExpected = ModelicaParser.parse(expected);
		ModelicaDocument moActual = ModelicaParser.parse(actual);
		assertTrue(moActual.getSystemModel().getDeclarations().stream().map(dec -> dec.getType())
				.collect(Collectors.toList())
				.containsAll(moExpected.getSystemModel().getDeclarations().stream()
						.map(dec -> dec.getType()).collect(Collectors.toList())));
		assertTrue(moActual.getSystemModel().getDeclarations().stream().map(dec -> dec.getId())
				.collect(Collectors.toList())
				.containsAll(moExpected.getSystemModel().getDeclarations().stream()
						.map(dec -> dec.getId()).collect(Collectors.toList())));
		assertTrue(moActual.getSystemModel().getEquations().stream().map(eq -> eq.getText())
				.collect(Collectors.toList())
				.containsAll(moExpected.getSystemModel().getEquations().stream()
						.map(eq -> eq.getText()).collect(Collectors.toList())));
	}
}
