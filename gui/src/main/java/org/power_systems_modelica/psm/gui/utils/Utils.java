package org.power_systems_modelica.psm.gui.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Utils {

	public static String translateLocation(String input) {

		String[] inputTokens = input.split("/");
		List<String> outputTokens = new ArrayList<String>();
		for (String token: inputTokens) {
			if (token.startsWith("$")) {
				Path path = Paths.get(System.getenv(token.replace("$", "")));
				token = path.toString();
			}
			outputTokens.add(token);
		}
		
		Path path = null;
		for (String token: outputTokens) {
			if (path == null) {
				path = Paths.get(token);
				continue;
			}
			
			Path p = path.resolve(token);
			path = p;
		}
		
		return path.toString();
	}

	public static Path findCasePath(Path path) throws IOException {

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
			for (Path entry : stream) {
				if (entry.toString().endsWith("ME.xml"))
					return entry;
				else if (entry.toString().endsWith("EQ.xml"))
					return entry;
			}
		}

		return null;
	}

	public static StringBuilder loadFile(String location, String file) throws IOException {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		Path path = Paths.get(location).resolve(file);
		InputStream inputStream = Files.newInputStream(path);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        bufferedReader.close();
        inputStream.close();
        
        return stringBuilder;
	}

	public static void saveFile(String location, String file, StringBuilder ddrContent) throws IOException {
		// TODO Auto-generated method stub
		
		Path path = Paths.get(location).resolve(file);
		OutputStream outputStream = Files.newOutputStream(path);
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
		bufferedWriter.append(ddrContent);
		bufferedWriter.close();
		outputStream.close();
	}

}
