package org.power_systems_modelica.psm.commons.test;

/*
 * #%L
 * Commons
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.power_systems_modelica.psm.commons.Version;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class VersionTest
{
	@Test
	public void testVersionNotEmpty()
	{
		assertTrue(!Version.VERSION.toString().equals(""));
	}

	@Test
	public void testVersionEquals()
	{
		Version v0 = Version.VERSION;
		String projectVersion = v0.getProjectVersion();
		String gitVersion = v0.getGitVersion();
		String gitBranch = v0.getGitBranch();
		long buildTimestamp = v0.getBuildTimestamp();

		Version v1 = new Version(projectVersion, gitVersion, gitBranch, buildTimestamp);
		assertEquals(v0, v1);
	}

	@Test
	public void testVersionNotEquals()
	{
		Version v0 = Version.VERSION;
		Version v2 = new Version("bad", "", "", 0L);
		assertFalse(v0.equals(v2));
	}
}