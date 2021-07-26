package com.test.weatherProj.utils;

import java.util.Properties;

public class ConfigLoader {
	private final Properties properties;
	private static ConfigLoader configLoader;

	private ConfigLoader() {
		properties = PropertyUtils.propertyLoader("src/test/resources/config/config.properties");
	}

	public static ConfigLoader getInstance() {
		if (configLoader == null) {
			configLoader = new ConfigLoader();
		}
		return configLoader;
	}

	public String getPropertyValue(String propertyName) {
		String prop = properties.getProperty(propertyName);
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property "+propertyName+" is not specified in the data.properties file");
	}
	public Properties getProperties() {
		return properties;
	}

}
