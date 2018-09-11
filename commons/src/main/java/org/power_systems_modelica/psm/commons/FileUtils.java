package org.power_systems_modelica.psm.commons;

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

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Comparator;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class FileUtils
{

	public static void deleteDirectory(Path path) throws IOException 
    {
        if (Files.exists(path, LinkOption.NOFOLLOW_LINKS))
    	{
    		Files.walk(path, FileVisitOption.FOLLOW_LINKS)
    				.sorted(Comparator.reverseOrder())
    				.map(Path::toFile)
//    				.peek(System.out::println)
    				.forEach(File::delete);
    	}
    }
}
