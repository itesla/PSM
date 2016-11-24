/**
 * Copyright (c) 2016, All partners of the iTesla project (http://www.itesla-project.eu/consortium)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.power_systems_modelica.psm.commons.tools;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.power_systems_modelica.psm.commons.Version;

import com.google.auto.service.AutoService;

import eu.itesla_project.commons.tools.Command;
import eu.itesla_project.commons.tools.Tool;

/**
 * @author Geoffroy Jamgotchian <geoffroy.jamgotchian at rte-france.com>
 */
@AutoService(Tool.class)
public class VersionTool implements Tool
{
	private static final Command COMMAND = new Command()
	{
		@Override
		public String getName()
		{
			return "version";
		}

		@Override
		public String getTheme()
		{
			throw new UnsupportedOperationException();
		}

		@Override
		public String getDescription()
		{
			throw new UnsupportedOperationException();
		}

		@Override
		public Options getOptions()
		{
			return new Options();
		}

		@Override
		public String getUsageFooter()
		{
			return null;
		}

		@Override
		public boolean isHidden()
		{
			return true;
		}
	};

	@Override
	public Command getCommand()
	{
		return COMMAND;
	}

	@Override
	public void run(CommandLine line) throws Exception
	{
		System.out.println(Version.VERSION);
	}
}
