package org.power_systems_modelica.psm.workflow.psm;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.ddr.DydFilesFromModelica;
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
		modelicaFileInit = config.getParameter("modelicaFileInit");
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
			Path pmoinit = null;
			if (modelicaFileInit != null) pmoinit = Paths.get(modelicaFileInit);

			new DydFilesFromModelica().mo2dyd(
					Paths.get(modelicaFile),
					pmoinit,
					Paths.get(ddrLocation));
			succeded();
		}
		catch (Exception x)
		{
			failed(x);
		}
	}

	private String	modelicaFile;
	private String	modelicaFileInit;
	private String	ddrLocation;
}
