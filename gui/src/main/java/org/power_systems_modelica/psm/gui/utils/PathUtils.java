package org.power_systems_modelica.psm.gui.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.power_systems_modelica.psm.gui.service.CaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class PathUtils {

	public static final Path	DATA_TEST		= Paths
			.get(System.getenv("PSM_DATA"))
			.resolve("test");

	public static final Path	DATA_TMP		= Paths
			.get(System.getenv("PSM_DATA"))
			.resolve("tmp");
	
	public static final Path	LIBRARY		= Paths
			.get(System.getenv("PSM_DATA"))
			.resolve("test").resolve("library");

	public static String translateLocation(String input)
	{

		String[] inputTokens = input.split("/");
		List<String> outputTokens = new ArrayList<String>();
		for (String token : inputTokens)
		{
			if (token.startsWith("$"))
			{
				Path path = Paths.get(System.getenv(token.replace("$", "")));
				token = path.toString();
			}
			outputTokens.add(token);
		}

		Path path = null;
		for (String token : outputTokens)
		{
			if (path == null)
			{
				path = Paths.get(token);
				continue;
			}

			Path p = path.resolve(token);
			path = p;
		}

		return path.toString();
	}

	public static Path findCasePath(Path path) throws IOException
	{

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path))
		{
			for (Path entry : stream)
			{
				if (entry.toString().endsWith("ME.xml"))
					return entry;
				else if (entry.toString().endsWith("EQ.xml"))
					return entry;
			}
		}

		return null;
	}

	public static StringBuilder loadFile(String location, String file) throws IOException
	{
		Path path = Paths.get(location).resolve(file);
		
		return loadFile(path);
	}

	public static StringBuilder loadFile(Path path) throws IOException
	{
		StringBuilder stringBuilder = new StringBuilder();

		InputStream inputStream = Files.newInputStream(path);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		while ((line = bufferedReader.readLine()) != null)
		{
			stringBuilder.append(line).append("\n");
		}
		bufferedReader.close();
		inputStream.close();

		return stringBuilder;
	}

	public static void saveFile(String location, String file, StringBuilder ddrContent)
			throws IOException
	{

		Path path = Paths.get(location).resolve(file);
		OutputStream outputStream = Files.newOutputStream(path);
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
		bufferedWriter.append(ddrContent);
		bufferedWriter.close();
		outputStream.close();
	}
	
	public static Properties loadDefaultConversionFile() throws IOException{
		Path defaultFile = DATA_TEST.resolve("cfg").resolve("conversion.properties");
		
		Properties properties = new Properties();
		InputStream is = Files.newInputStream(defaultFile);
		properties.load(is);
		is.close();
		
		String workflowFile = properties.getProperty("conversionPropertiesFile");
		
		Properties props = new Properties();
		Path workflowPath = Paths.get(workflowFile);
    	is = Files.newInputStream(workflowPath);
    	props.load( is );
    	is.close();
    	
    	return props;
	}
	
	public static Properties loadConversionFile(GuiFileChooser fileChooser, Stage stage, String location) throws IOException{
		
        //Set extension filter
		fileChooser.setDetails(location, "Properties files (*.properties)", "*.properties");
		Properties props = new Properties();
		
        File selectedFile = fileChooser.showOpenDialog(stage);
        if(selectedFile != null){
        	InputStream is = new FileInputStream(selectedFile);
        	props.load( is );
        	is.close();
        }
        
        return props;
	}

	public static void saveConversionFile(GuiFileChooser fileChooser, Stage stage, String location, Properties props) throws IOException{
		  
        //Set extension filter
		fileChooser.setDetails(location, "Properties files (*.properties)", "*.properties");
		
        //Show save file dialog
        File selectedFile = fileChooser.showSaveDialog(stage);
        if(selectedFile != null){
        	OutputStream out = new FileOutputStream(selectedFile);
            props.store(out, "Conversion configuration file");
            out.close();

            Properties defaultProperties = new Properties();
            defaultProperties.setProperty("conversionPropertiesFile", selectedFile.getAbsolutePath());
            Path defaultFile = DATA_TEST.resolve("cfg").resolve("conversion.properties");
            out = Files.newOutputStream(defaultFile);
            defaultProperties.store(out, "Default conversion configuration file");
            out.close();
        }
        
	}
	
	public static Properties loadDefaultSimulationFile() throws IOException{
		Path defaultFile = DATA_TEST.resolve("cfg").resolve("simulation.properties");
		
		Properties properties = new Properties();
		InputStream is = Files.newInputStream(defaultFile);
		properties.load(is);
		is.close();
		
		String workflowFile = properties.getProperty("simulationPropertiesFile");
		
		Properties props = new Properties();
		Path workflowPath = Paths.get(workflowFile);
    	is = Files.newInputStream(workflowPath);
    	props.load( is );
    	is.close();
    	
    	return props;
	}
	
	public static Properties loadSimulationFile(GuiFileChooser fileChooser, Stage stage, String location) throws IOException{
		
        //Set extension filter
		fileChooser.setDetails(location, "Properties files (*.properties)", "*.properties");
		Properties props = new Properties();
		
        File selectedFile = fileChooser.showOpenDialog(stage);
        if(selectedFile != null){
        	InputStream is = new FileInputStream(selectedFile);
        	props.load( is );
        	is.close();
        }
        
        return props;
	}

	public static void saveSimulationFile(GuiFileChooser fileChooser, Stage stage, String location, Properties props) throws IOException{
		  
        //Set extension filter
		fileChooser.setDetails(location, "Properties files (*.properties)", "*.properties");
		
        //Show save file dialog
        File selectedFile = fileChooser.showSaveDialog(stage);
        if(selectedFile != null){
        	OutputStream out = new FileOutputStream(selectedFile);
            props.store(out, "Simulation configuration file");
            out.close();

            Properties defaultProperties = new Properties();
            defaultProperties.setProperty("simulationPropertiesFile", selectedFile.getAbsolutePath());
            Path defaultFile = DATA_TEST.resolve("cfg").resolve("simulation.properties");
            out = Files.newOutputStream(defaultFile);
            defaultProperties.store(out, "Default simulation configuration file");
            out.close();
        }
        
	}
	
	public static boolean saveAsMoFile(GuiFileChooser fileChooser, Stage stage, String location, String file, StringBuilder ddrContent) throws IOException{ 
		  
        //Set extension filter
		fileChooser.setDetails(location, "Modelica files (*.mo)", "*.mo");
        fileChooser.setInitialFileName(file);
        
        return saveAsFile(fileChooser, stage, ddrContent);
	}

	public static boolean saveAsDdrFile(GuiFileChooser fileChooser, Stage stage, String location, String file, StringBuilder ddrContent) throws IOException{ 
		  
        //Set extension filter
		fileChooser.setDetails(location, "DDR files (*.dyd;*.par)", "*.dyd", "*.par");
        fileChooser.setInitialFileName(file);
        
        return saveAsFile(fileChooser, stage, ddrContent);
	}
	
	private static boolean saveAsFile(GuiFileChooser fileChooser, Stage stage, StringBuilder ddrContent) throws IOException{
        
        //Show save file dialog
        File selectedFile = fileChooser.showSaveDialog(stage);
        
        if(selectedFile != null){
            saveFile(selectedFile.getParent(), selectedFile.getName(), ddrContent);
            return true;
        }
        
        return false;
    }

	public static boolean existsFile(String location, String file)
	{

		try
		{
			Path path = Paths.get(location).resolve(file);
			return Files.exists(path);
		}
		catch (InvalidPathException e)
		{
			return false;
		}
	}

	public static String directoryOutput(Stage stage, String initialDirectory) {
		
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setInitialDirectory(new File(initialDirectory));
		
		File selectedFolder = chooser.showDialog(stage);
		if(selectedFolder != null){
			return selectedFolder.getPath();
		}
		
		return null;
	}
	
	public static String selectCsvFile(GuiFileChooser fileChooser, Stage stage, String initialDirectory) {
		
		fileChooser.setDetails(initialDirectory, "CSV files (*.csv)", "*.csv");
        File selectedFile = fileChooser.showOpenDialog(stage);
		if(selectedFile != null){
			return selectedFile.getPath();
		}
		
		return null;
	}
	
	public static Properties getGUIProperties() {

		Properties properties = new Properties();
		try {
			try (InputStream is = Files.newInputStream(DATA_TEST.resolve("cfg").resolve("gui.properties"))) {
				properties.load(is);
			}
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
		return properties;
	}

	private static final Logger LOG = LoggerFactory.getLogger(PathUtils.class);
}
