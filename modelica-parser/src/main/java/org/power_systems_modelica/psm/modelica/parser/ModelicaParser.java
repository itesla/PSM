package org.power_systems_modelica.psm.modelica.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.parser.MoLexer;
import org.power_systems_modelica.psm.modelica.parser.MoParser;

public class ModelicaParser
{
	// FIXME Allow complex expressions as equations (omegaRef = sum(g.omega*g.SN*g.HIn)/sum(g.SN*g.HIn)

	public ModelicaDocument parse(Path mo) throws FileNotFoundException, IOException
	{
		MoLexer lexer = new MoLexer(new ANTLRInputStream(new FileReader(mo.toFile())));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		MoParser parser = new MoParser(tokens);
		parser.document();
		ModelicaDocument mod = parser.modelicaDocument;
		return mod;
	}
}
