package org.power_systems_modelica.psm.commons;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Comparator;

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
