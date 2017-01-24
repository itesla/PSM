package org.power_systems_modelica.psm.workflow.psm;

import static org.power_systems_modelica.psm.workflow.Workflow.ResultsScope.SCOPE_GLOBAL;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

public class ModelicaSimulatorTaskResults extends WorkflowTask
{

	public ModelicaSimulatorTaskResults(String id)
	{
		super(id);
	}

	@Override
	public String getName()
	{

		return "Modelica Dynamic Simulation Results";
	}

	@Override
	public void configure(Configuration config)
	{

		source = config.getParameter("source");
		if (source == null)
			source = "simres";
		target = config.getParameter("target");
		createFilteredMat = false;
		String screateFilteredMat = config.getParameter("createFilteredMat");
		if (screateFilteredMat != null)
			createFilteredMat = Boolean.valueOf(screateFilteredMat);
	}

	@Override
	public void run()
	{
		running();

		Path modelicaEngineWorkingDir = Paths.get(workflow.getResults(source).toString());
		System.out.println("Source: " + source);
		System.out.println("Source result: " + modelicaEngineWorkingDir);
		System.out.println("Target: " + target);

		try
		{
			Files.walk(modelicaEngineWorkingDir, FileVisitOption.FOLLOW_LINKS)
					.filter(path -> path.toString().contains("_res") &&
							(path.toString().endsWith(".mat") || path.toString().endsWith(".csv")))
					.map(Path::toFile).forEach(file -> {
						System.out.println("File: " + file.toPath());
						try
						{
							Files.copy(file.toPath(), (new File(target, file.getName())).toPath(),
									StandardCopyOption.REPLACE_EXISTING);
						}
						catch (IOException e)
						{
							failed();
						}
					});

			succeded();

			publish(SCOPE_GLOBAL,
					"simres_output",
					target);
		}
		catch (Exception x)
		{
			failed(x);
		}
	}

	private String	source;
	private String	target;
	private boolean	createFilteredMat;
}
