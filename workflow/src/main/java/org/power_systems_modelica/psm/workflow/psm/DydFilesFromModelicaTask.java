package org.power_systems_modelica.psm.workflow.psm;

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
		ddrLocation = config.getParameter("ddrLocation");
		modelicaFile = config.getParameter("modelicaFile");
	}

	@Override
	public void run()
	{
		running();
		try
		{
			String name = Paths.get(modelicaFile).getFileName().toString().replace(".mo", "");
			String dydname = name.concat(".dyd");
			String parname = name.concat(".par");
			DydFilesFromModelica.mo2dyd(
					Paths.get(modelicaFile),
					Paths.get(ddrLocation),
					dydname,
					parname);
			succeded();
		}
		catch (Exception x)
		{
			failed(x);
		}
	}

	private String	modelicaFile;
	private String	ddrLocation;
}
