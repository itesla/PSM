package org.power_systems_modelica.psm.network.import_;

import java.nio.file.Path;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.iidm.datasource.ZipFileDataSource;
import eu.itesla_project.iidm.import_.Importer;
import eu.itesla_project.iidm.import_.Importers;
import eu.itesla_project.iidm.network.Network;
import eu.itesla_project.iidm.parameters.Parameter;

public class StaticNetworkImporter
{
	public static Network import_(Path file)
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
			else if (basename.endsWith(".zip")) n = Importers.import_("CIM1",
					new ZipFileDataSource(path, basename.toString(), basename1), parameters);
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

	private static final Logger LOG = LoggerFactory.getLogger(StaticNetworkImporter.class);
}
