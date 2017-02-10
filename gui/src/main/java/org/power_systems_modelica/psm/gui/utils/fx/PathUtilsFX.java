package org.power_systems_modelica.psm.gui.utils.fx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import org.power_systems_modelica.psm.gui.utils.PathUtils;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class PathUtilsFX
{

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
            Path defaultFile = PathUtils.DATA_TEST.resolve("cfg").resolve("conversion.properties");
            out = Files.newOutputStream(defaultFile);
            defaultProperties.store(out, "Default conversion configuration file");
            out.close();
        }
        
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
            Path defaultFile = PathUtils.DATA_TEST.resolve("cfg").resolve("simulation.properties");
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
        	PathUtils.saveFile(selectedFile.getParent(), selectedFile.getName(), ddrContent);
            return true;
        }
        
        return false;
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
	
}
