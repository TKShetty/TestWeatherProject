package com.test.weatherProj.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * This class is used to maintain all the browser specific options
 * 
 * @author TShetty
 *
 */
public class OptionsManager {

	private Properties prop;
	private ChromeOptions cromeOptions;
	private FirefoxOptions ffOptions;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		cromeOptions = new ChromeOptions();
		cromeOptions.addArguments("--disable-notifications");
		if (Boolean.parseBoolean(prop.getProperty("headless")))
			cromeOptions.addArguments("--headless");
		return cromeOptions;
	}

	public FirefoxOptions getFirefoxOptions() {
		ffOptions = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless")))
			ffOptions.addArguments("--headless");
		return ffOptions;
	}

}
