package org.power_systems_modelica.psm.network.import_;

/*
 * #%L
 * Static network importer
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.nio.file.Path;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.powsybl.commons.datasource.ZipFileDataSource;
import com.powsybl.iidm.import_.Importer;
import com.powsybl.iidm.import_.Importers;
import com.powsybl.iidm.network.Network;
import com.powsybl.iidm.network.Branch;
import com.powsybl.iidm.parameters.Parameter;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class StaticNetworkImporter
{
	public static Network import_(Path file)
	{
		return import_(file, MIN_Xpu);
	}

	public static Network import_(Path file, Float minXpu)
	{
		Path path = file.getParent();
		Path basename = file.getFileName();
		// Get rid of suffix and extension
		String basename1 = basename.toString().replaceFirst("_(ME|EQ|SV|TP).xml", "");
		basename1 = basename1.replaceFirst(".zip", "");
		basename1 = basename1.replaceFirst(".uct", "");
		basename1 = basename1.replaceFirst(".iidm", "");

		Properties parameters = new Properties();
		parameters.put("usePsseNamingStrategy", "true");

		Network n = null;
		try
		{
			LOG.info("importing file [" + basename1 + "]");
			if (basename.toString().endsWith(".xml"))
				n = Importers.importData("CIM1", path.toString(), basename1, parameters);
			else if (basename.toString().endsWith(".iidm"))
				n = Importers.importData("XIIDM", path.toString(), basename1, parameters);
			else if (basename.toString().endsWith(".zip"))
			{
				ZipFileDataSource zipDataSource = new ZipFileDataSource(path, basename.toString(),
						basename1);
				n = Importers.importData("CIM1",
						zipDataSource, parameters);
			}

			// 
			
			// TODO How do we should configure minimum impedance fixing a minimum reactance
			if (minXpu != null) fixLowImpedances(n, minXpu);
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

	public static void fixLowImpedances(Network n, Float minXpu)
	{
		n.getLineStream()
				.filter(l -> getXpu(l, l.getR(), l.getX()) < minXpu)
				.forEach(l -> l.setX(fixedReactanceForLowImpedance(l, minXpu, l.getX())));
		n.getTwoWindingsTransformerStream()
				.filter(tr -> getXpu(tr, tr.getR(), tr.getX()) < minXpu)
				.forEach(tr -> tr.setX(fixedReactanceForLowImpedance(tr, minXpu, tr.getX())));
	}
	
	private static double getXpu(Branch<?> branch, double R, double X)
	{
		final float SNREF = 100.0f;
		double V = branch.getTerminal2().getVoltageLevel().getNominalV();
		if (Double.isNaN(V)) return Float.NaN;
		double K = (V * V) / SNREF;
		double Xpu = X / K;
		return Math.abs(Xpu);
	}

	private static double fixedReactanceForLowImpedance(
			Branch<?> branch,
			double Xpu,
			double X0)
	{
		final float SNREF = 100.0f;
		double V = branch.getTerminal2().getVoltageLevel().getNominalV();
		if (Double.isNaN(V)) return Float.NaN;

		// Set the new value to the minimum allowed reactance
		// (it comes expressed in pu base SNREF, and we want to store it in Siemens)
		double Z = (V * V) / SNREF;
		double X1 = Xpu * Z;

		// Preserve the sign of X0
		X1 *= Math.signum(X0);

		LOG.warn(
				"Low impedance branch, reactance {} changed to {} at branch {}, nominal Voltage {}",
				X0,
				X1,
				branch,
				V);
		return X1;
	}

	private static final Logger	LOG						= LoggerFactory
			.getLogger(StaticNetworkImporter.class);

	private static final Float	MIN_Xpu			= 0.0002f;
}
