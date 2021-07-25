package com.test.weatherProj.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.test.weatherProj.factory.DriverFactory;

public class BaseTest {
	public WebDriver driver;
	public DriverFactory driverFactory;
	public Properties prop;
	
	@Parameters({"browser", "browserversion"})
	@BeforeTest
	public void setUp(String browserName, String browserVersion) {
		driverFactory = new DriverFactory();
		prop = driverFactory.initializeProperties();
		if (browserName != null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
		}
		driver = driverFactory.intializeDriver(prop);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
