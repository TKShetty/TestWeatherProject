package com.test.weatherProj.utils;

import java.util.Properties;



public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader(){
        properties = PropertyUtils.propertyLoader("src/test/resources/config/data.properties");
    }

    public static DataLoader getInstance(){
        if(dataLoader == null){
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

	public String getPropertyValue(String propertyName) {
		String prop = properties.getProperty(propertyName);
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property "+propertyName+" is not specified in the data.properties file");
	}

}
