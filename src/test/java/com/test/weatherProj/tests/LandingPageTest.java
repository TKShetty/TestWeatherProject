package com.test.weatherProj.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.test.weatherProj.api.StatusCode;
import com.test.weatherProj.applicationApi.WeatherAPI;
import com.test.weatherProj.pages.HomePage;
import com.test.weatherProj.utils.DataLoader;

import io.restassured.response.Response;

public class LandingPageTest extends BaseTest {

	public WebDriver driver;

	@BeforeMethod
	public void initialize() throws IOException {
		driver = getDriver();
	}

	@Test
	public void verifyTemperature() {
		String cityName=DataLoader.getInstance().getPropertyValue("CityName");
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		HomePage homePage = new HomePage(driver);
		try {
			/*WeatherForecastPage wp = homePage.searchCity(cityName);
			String uiTempReading = wp.getCurrentTemperature();*/
			Response response =WeatherAPI.getCityWeatherAPIResponse(cityName);
			assertStatusCode(response.statusCode(), StatusCode.CODE_200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    private void assertStatusCode(int actualStatusCode, StatusCode statusCode){
        assertEquals(actualStatusCode, statusCode.code);
    }
}
