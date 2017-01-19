package org.power_systems_modelica.psm.network.import_;

import java.nio.file.Path;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.iidm.datasource.ZipFileDataSource;
import eu.itesla_project.iidm.import_.Importer;
import eu.itesla_project.iidm.import_.Importers;
import eu.itesla_project.iidm.network.Network;
import eu.itesla_project.iidm.network.TwoTerminalsConnectable;
import eu.itesla_project.iidm.parameters.Parameter;

public class StaticNetworkImporter
{
	public static Network import_(Path file)
	{
		return import_(file, MIN_X_PU_DEFAULT);
	}

	public static Network import_(Path file, Float minx)
	{
		Path path = file.getParent();
		Path basename = file.getFileName();
		// Get rid of suffix and extension
		String basename1 = basename.toString().replaceFirst("_(ME|EQ|SV|TP).xml", "");
		basename1 = basename1.replaceFirst(".zip", "");
		basename1 = basename1.replaceFirst(".uct", "");

		Properties parameters = new Properties();
		parameters.put("usePsseNamingStrategy", "true");

		Network n = null;
		try
		{
			LOG.info("importing file [" + basename1 + "]");
			if (basename.toString().endsWith(".xml"))
				n = Importers.import_("CIM1", path.toString(), basename1, parameters);
			else if (basename.toString().endsWith(".zip"))
			{
				ZipFileDataSource zipDataSource = new ZipFileDataSource(path, basename.toString(),
						basename1);
				n = Importers.import_("CIM1",
						zipDataSource, parameters);
			}

			// TODO How do we should configure minimum reactance
			// (maybe use a configuration file similar to other iPST modules)
			if (minx != null) fixLowReactances(n, minx);
		}
		catch (Exception x)
		{
			LOG.error("during import " + x);
			for (StackTraceElement ste : x.getStackTrace())
				LOG.error("    " + ste);
			Importer importer = Importers.getImporter("CIM1");
			if (importer != null)
			{
				LOG.info("Default parameters from CIM1 importer:");
				for (Parameter param : importer.getParameters())
					LOG.info("\t" + param.getName() + " : " + param.getDefaultValue());
			}
		}
		return n;
	}

	public static void fixLowReactances(Network n, float minx)
	{
		n.getLineStream()
				.filter(l -> Math.abs(l.getX()) < minx)
				.forEach(l -> l.setX(fixedLowReactance(l.getX(), minx, l)));
		n.getTwoWindingsTransformerStream()
				.filter(tr -> Math.abs(tr.getX()) < minx)
				.forEach(tr -> tr.setX(fixedLowReactance(tr.getX(), minx, tr)));
	}

	private static float fixedLowReactance(float X, float minx, TwoTerminalsConnectable<?> branch)
	{
		final float SNREF = 100.0f;
		float V = branch.getTerminal2().getVoltageLevel().getNominalV();
		if (Float.isNaN(V)) return Float.NaN;

		// Set the new value to the minimum allowed reactance
		// (it comes expressed in pu base SNREF, and we want to store it in Siemens)
		float Z = (V * V) / SNREF;
		float X1 = minx * Z;
		
		// Preserve the original sign of X
		float sign = (X == 0.0f ? 1.0f : Math.signum(X));
		X1 *= sign;
		
		LOG.warn("Low reactance {} changed to {} at branch {}, nominal Voltage {}",
				X,
				X1,
				branch,
				V);
		return X1;
	}

	private static final Logger	LOG					= LoggerFactory
			.getLogger(StaticNetworkImporter.class);

	public static final Float	MIN_X_PU_DEFAULT	= 0.002f;
}
