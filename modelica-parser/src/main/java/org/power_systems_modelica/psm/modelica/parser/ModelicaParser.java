package org.power_systems_modelica.psm.modelica.parser;

/*
 * #%L
 * Modelica simple model parser
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelicaParser
{
	// FIXME Allow complex expressions as equations (omegaRef = sum(g.omega*g.SN*g.HIn)/sum(g.SN*g.HIn)

	static public ModelicaDocument parse(Path mo) throws FileNotFoundException, IOException
	{
		MoLexer lexer = new MoLexer(new ANTLRInputStream(new FileReader(mo.toFile())));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		MoParser parser = new MoParser(tokens);
		parser.document();
		ModelicaDocument mod = parser.modelicaDocument;
		return mod;
	}
}
