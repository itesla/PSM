package org.power_systems_modelica.psm.gui.utils;

import java.io.IOException;
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

}
