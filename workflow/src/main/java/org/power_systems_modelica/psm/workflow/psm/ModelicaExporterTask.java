package org.power_systems_modelica.psm.workflow.psm;

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
		includePsmAnnotations = false;
		String sincludePsmAnnotations = config.getParameter("includePsmAnnotations");
		if (sincludePsmAnnotations != null)
			includePsmAnnotations = Boolean.valueOf(sincludePsmAnnotations);
	}

	@Override
	public void run()
	{
		running();

		ModelicaDocument mo = (ModelicaDocument) workflow.getResults(source);
		try
		{
			ModelicaTextPrinter.print(mo, Paths.get(target), includePsmAnnotations);
			succeded();
		}
		catch (Exception x)
		{
			failed(x);
		}
	}
	
	@Override
	public void cancel()
	{
		// TODO Auto-generated method stub
	}

	private String	source;
	private String	target;
	private boolean	includePsmAnnotations;
}
