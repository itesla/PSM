package org.power_systems_modelica.psm.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlatformConfig {

	public static final Path CONFIG_DIR;

	private static PlatformConfig defaultConfig;

	private static final Logger LOGGER = LoggerFactory.getLogger(PlatformConfig.class);

	private final Path configDir;

	private final FileSystem fs;

	static {
		String configDir = System.getProperty("config.dir");
		if (configDir != null) {
			CONFIG_DIR = Paths.get(configDir);
		} else {
			CONFIG_DIR = Paths.get(System.getProperty("user.home"), ".psm");
		}
		try {
			if (!(Files.isDirectory(CONFIG_DIR)))
				Files.createDirectories(CONFIG_DIR);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static synchronized PlatformConfig defaultConfig() {
		if (defaultConfig == null) {
			defaultConfig = new PlatformConfig(CONFIG_DIR, FileSystems.getDefault());
		}
		return defaultConfig;
	}

	public PlatformConfig(Path configDir, FileSystem fs) {
		this.configDir = configDir;
		this.fs = fs;
		LOGGER.info("Platform configuration defined by .properties files of directory {}", configDir);
	}

	private Path getModulePath(String name) {
		return configDir.resolve(name + ".properties");
	}

	public ModuleConfig getModuleConfig(String name) {
		ModuleConfig moduleConfig = getModuleConfigIfExists(name);
		if (moduleConfig == null) {
			throw new RuntimeException("Module " + name + " not found");
		}
		return moduleConfig;
	}

	public ModuleConfig getModuleConfigIfExists(String name) {
		Path path = getModulePath(name);
		if (!Files.exists(path)) {
			return null;
		}
		LOGGER.info("Reading property file {}", path);
		Properties properties = new Properties();
		try {
			try (InputStream is = Files.newInputStream(path)) {
				properties.load(is);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return new MapModuleConfig(properties, fs);
	}
}
