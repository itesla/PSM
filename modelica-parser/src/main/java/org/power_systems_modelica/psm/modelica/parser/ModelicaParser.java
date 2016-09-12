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
	// TODO Allow some parameters defined but not initialized (omegaRef)
	// TODO Allow expressions in equations (omegaRef = sum(g.omega*g.SN*g.HIn)/sum(g.SN*g.HIn)
	// TODO Allow assignments from other vars in model instantiation arguments, example:
	// iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN____8_SM (SNREF = SNREF, ...)

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
