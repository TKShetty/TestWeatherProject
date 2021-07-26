package com.test.weatherProj.tests;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.test.weatherProj.factory.DriverFactory;

public class BaseTest {
	private WebDriver driver;
	private DriverFactory driverFactory;
	private Properties prop;
	private static Logger log = LogManager.getLogger(BaseTest.class.getName());

	@Parameters({ "browser", "browserversion" })
	@BeforeTest
	public void setUp(String browserName, String browserVersion) {
		driverFactory = new DriverFactory();
		prop = driverFactory.initializeProperties();
		if (browserName != null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
		}
		driver = driverFactory.intializeDriver(prop);
		log.info("Driver is initialized");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		log.info("Navigated to Home page");
	}

	public WebDriver getDriver() {
		return driver;
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
