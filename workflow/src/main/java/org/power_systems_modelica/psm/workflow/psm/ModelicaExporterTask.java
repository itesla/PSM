package org.power_systems_modelica.psm.workflow.psm;

import java.io.PrintWriter;
import java.nio.file.Paths;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

public class ModelicaExporterTask extends WorkflowTask
{
	public ModelicaExporterTask(String id)
	{
		super(id);
	}

	@Override
	public String getName()
	{
		return "Modelica exporter";
	}

	@Override
	public void configure(Configuration config)
	{
		source = config.getParameter("source");
		if (source == null) source = "mo";
		target = config.getParameter("target");
	}

	@Override
	public void run()
	{
		running();

		ModelicaDocument mo = (ModelicaDocument) workflow.getResults(source);
		ModelicaTextPrinter mop = new ModelicaTextPrinter(mo);
		try (PrintWriter out = new PrintWriter(Paths.get(target).toFile());)
		{
			mop.print(out);
			succeded();
		}
		catch (Exception x)
		{
			failed(x);
		}
	}

	private String	source;
	private String	target;
}
