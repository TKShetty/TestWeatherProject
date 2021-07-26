package com.test.weatherProj.tests;

import static org.testng.Assert.assertEquals;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.test.weatherProj.api.StatusCode;
import com.test.weatherProj.factory.DriverFactory;
import com.test.weatherProj.utils.ConfigLoader;

public class BaseTest {
	private WebDriver driver;
	private DriverFactory driverFactory;
	private Properties prop;
	private static Logger log = LogManager.getLogger(BaseTest.class.getName());

	@Parameters({ "browser", "browserversion" })
	@BeforeMethod
	public void setUp(String browserName, String browserVersion) {
		ConfigLoader configLoader = ConfigLoader.getInstance();
		prop = configLoader.getProperties();
		driverFactory = new DriverFactory();
		if (browserName != null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
		}
		driver = driverFactory.intializeDriver(prop);
		log.info("Driver is initialized");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(configLoader.getPropertyValue("url"));
		log.info("Navigated to Home page");
	}

	public WebDriver getDriver() {
		return driver;
	}

	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}

	public void assertStatusCode(int actualStatusCode, StatusCode statusCode) {
		assertEquals(actualStatusCode, statusCode.code);
	}

}
