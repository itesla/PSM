package org.psm.openmodelica.integration.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZipWriter {
	
	public ZipWriter(String outputZipName, Path directoryToZip, Path outputDirectory, DirectoryStream.Filter<Path> filesFilter) {
		this.outputZipName = outputZipName;
		this.directoryToZip = directoryToZip;
		this.outputDirectory = outputDirectory;
		this.filesFilter = filesFilter;
	}

	private void addFileToZip(Path file, ZipOutputStream zos) {		
		if(!this.addedFilesList.contains(file) && !file.toFile().getName().equalsIgnoreCase(this.outputZipName)) {
			File inputFilePath = file.toFile();
			String inputFileName = file.toFile().getName();
			
			try (FileInputStream fis = new FileInputStream(inputFilePath)) {
				ZipEntry zipEntry = new ZipEntry(inputFileName);
				zos.putNextEntry(zipEntry);
	
				byte[] bytes = new byte[1024];
				int length;
				while ((length = fis.read(bytes)) >= 0) {
					zos.write(bytes, 0, length);
				}
	
				zos.closeEntry();
				fis.close();
				
				LOG.info("File {} added to zip. ", inputFileName);
				this.addedFilesList.add(inputFilePath);
			} catch (IOException e) {
				throw new ZipParsingException("Unable to process " + inputFileName, e);
			}
		}
	}

	public void createZip() {
		String zipFile = this.outputDirectory + "/" + this.outputZipName;
		
		try (ZipOutputStream zipStream = new ZipOutputStream(new FileOutputStream(zipFile))) {

			try(DirectoryStream<Path> dirStream = Files.newDirectoryStream(this.directoryToZip, filesFilter)) {
				dirStream.forEach(path -> addFileToZip(path, zipStream));

				LOG.info("Zip file created in " + this.outputDirectory.toString());
			}
			
		} catch (IOException | ZipParsingException e) {
			LOG.error("Error zipping files. {}", e);
		}
	}

	private class ZipParsingException extends RuntimeException {
		public ZipParsingException(String reason, Exception inner) {
			super(reason, inner);
		}
	}

	private String 								outputZipName;
	private Path								outputDirectory;
	private Path								directoryToZip;
	private Set<File>							addedFilesList	=	new HashSet<File>();
	private DirectoryStream.Filter<Path>		filesFilter;
	
	private static final Logger LOG				=	LoggerFactory.getLogger(ZipWriter.class);
}
