package org.power_systems_modelica.psm.dymola.integration.utils;

/*
 * #%L
 * Dynamic simulation using Dymola
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Silvia Machado <machados at aia.es>
 */
public class ZipWriter
{

	public ZipWriter(String outputZipName, Path directoryToZip, Path outputDirectory,
			DirectoryStream.Filter<Path> filesFilter)
	{
		this.outputZipName = outputZipName;
		this.directoryToZip = directoryToZip;
		this.outputDirectory = outputDirectory;
		this.filesFilter = filesFilter;
	}

	private void getAllFilesToZip(File dir, List<Path> fileList)
	{
		Stream.of(dir.listFiles()).forEach(file -> {
			fileList.add(file.toPath());
			if (file.isDirectory())
			{
				getAllFilesToZip(file, fileList);
			}
		});
	}

	private void addFileToZip(Path file, ZipOutputStream zos)
	{
		if (!this.addedFilesList.contains(file)
				&& !file.toFile().getName().equalsIgnoreCase(this.outputZipName))
		{
			File inputFilePath = file.toFile();
			String inputFileName = null;
			try (FileInputStream fis = new FileInputStream(inputFilePath))
			{
				inputFileName = file.toString().substring(directoryToZip.toFile().getAbsolutePath().length()+1, (int) file.toString().length());
				
				ZipEntry zipEntry = new ZipEntry(inputFileName);
				zos.putNextEntry(zipEntry);

				byte[] bytes = new byte[1024];
				int length;
				while ((length = fis.read(bytes)) >= 0)
				{
					zos.write(bytes, 0, length);
				}

				zos.closeEntry();
				fis.close();

//				LOG.info("File {} added to zip. ", inputFileName);
				this.addedFilesList.add(inputFilePath);
			}
			catch (IOException e)
			{
				throw new ZipParsingException("Unable to process " + inputFileName, e);
			}
		}
	}

	public void createZip() {
		String zipFile = this.outputDirectory + "/" + this.outputZipName;
		
		try (ZipOutputStream zipStream = new ZipOutputStream(new FileOutputStream(zipFile))) {
				//FIXME add to the zip all the iPSL structure
				getAllFilesToZip(this.directoryToZip.toFile(), this.filesList);
				
				for(Path p : filesList) {
					if(!p.toFile().isDirectory()) addFileToZip(p, zipStream);					
				}

				LOG.info("Zip file created in " + this.outputDirectory.toString());			
		} catch (IOException | ZipParsingException e) {
			LOG.error("Error zipping files. {}", e);
		}
	}

	private class ZipParsingException extends RuntimeException
	{

		private static final long serialVersionUID = 1L;

		public ZipParsingException(String reason, Exception inner)
		{
			super(reason, inner);
		}
	}

	private String							outputZipName;
	private Path							outputDirectory;
	private Path							directoryToZip;
	private Set<File>						addedFilesList	= new HashSet<File>();
	private List<Path>						filesList		= new ArrayList<Path>();
	private DirectoryStream.Filter<Path>	filesFilter;

	private static final Logger				LOG				= LoggerFactory
			.getLogger(ZipWriter.class);
}