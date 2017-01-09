package org.power_systems_modelica.psm.workflow.psm;

import static org.power_systems_modelica.psm.workflow.Workflow.ResultsScope.SCOPE_GLOBAL;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

public class ModelicaParserTask extends WorkflowTask
{
	public ModelicaParserTask(String id)
	{
		super(id);
	}

	@Override
	public String getName()
	{
		return "Modelica parser";
	}

	@Override
	public void configure(Configuration config)
	{
		source = config.getParameter("source");
		modelicaDocument = config.getParameter("modelicaDocument");
	}

	@Override
	public void run()
	{
		running();

		try
		{
			Path modelicaFile = Paths.get(source);
			ModelicaDocument mo = ModelicaParser.parse(modelicaFile);
			publish(SCOPE_GLOBAL, modelicaDocument, mo);
			succeded();
		}
		catch (Exception x)
		{
			failed(x);
		}
	}

	private String	source;
	private String	modelicaDocument;
}
