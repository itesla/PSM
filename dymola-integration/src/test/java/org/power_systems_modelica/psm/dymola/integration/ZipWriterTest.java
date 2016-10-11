package org.power_systems_modelica.psm.dymola.integration;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.power_systems_modelica.psm.dymola.integration.utils.ZipWriter;

public class ZipWriterTest {

	@Test
	public void test() {
		String outputName 		= "sampletest.zip";
		Path directoryToZip 	= TEST_SAMPLES.resolve("models_files");
		Path outputDirectory	= TEST_SAMPLES;
		
		DirectoryStream.Filter<Path> filesFilter =  (entry)-> {
            File f = entry.toFile();
            return f.getName().endsWith(".mo");
		}; 

		
		ZipWriter zipper = new ZipWriter(outputName, directoryToZip, outputDirectory, filesFilter);
		zipper.createZip();
	
		assertEquals(true, Files.exists(Paths.get(outputDirectory + "/" + outputName)));
	}

	private static final Path TEST_SAMPLES = Paths.get(System.getenv("PSM_DATA"))
			.resolve("test")
			.resolve("sampletest");
}
