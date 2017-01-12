package org.power_systems_modelica.psm.workflow.psm;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.mo2dyd.DydFilesFromModelica;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

public class DydFilesFromModelicaTask extends WorkflowTask
{
	public DydFilesFromModelicaTask(String id)
	{
		super(id);
	}

	@Override
	public String getName()
	{
		return "DYD repository from Modelica file";
	}

	@Override
	public void configure(Configuration config)
	{
		modelicaFile = config.getParameter("modelicaFile");
		modelicaInitPath = config.getParameter("modelicaInitPath");
		ddrLocation = config.getParameter("ddrLocation");
	}

	@Override
	public void run()
	{
		running();
		try
		{
			// If there is no Modelica file with initialization models
			// they will be built (inferred) from simulation models
			Path moInitPath = null;
			if (modelicaInitPath != null) moInitPath = Paths.get(modelicaInitPath);

			new DydFilesFromModelica(
					Paths.get(modelicaFile),
					moInitPath,
					Paths.get(ddrLocation)).mo2dyd();
			succeded();
		}
		catch (Exception x)
		{
			failed(x);
		}
	}

	private String	modelicaFile;
	private String	modelicaInitPath;
	private String	ddrLocation;
}
