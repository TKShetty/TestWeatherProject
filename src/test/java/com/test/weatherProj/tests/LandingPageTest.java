package com.test.weatherProj.tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.test.weatherProj.pages.HomePage;
import com.test.weatherProj.pages.WeatherForecastPage;

public class LandingPageTest extends BaseTest {

	public WebDriver driver;

	@BeforeMethod
	public void initialize() throws IOException {
		driver = getDriver();
	}
	@Parameters({ "cityName" })
	@Test
	public void verifyTemperature(String cityName) {
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		HomePage homePage = new HomePage(driver);
		try {
			WeatherForecastPage wp = homePage.searchCity(cityName);
			String uitemp = wp.getCurrentTemperature();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
