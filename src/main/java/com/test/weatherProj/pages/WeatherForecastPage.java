package com.test.weatherProj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.google.common.base.CharMatcher;
import com.test.weatherProj.utils.ElementUtil;

public class WeatherForecastPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By currentTemperatureLabel = By.xpath("//div[@class='cur-con-weather-card__body']//div[@class='temp']");

	public WeatherForecastPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	public String getCurrentTemperature() throws Exception {
		String temp = elementUtil.doGetText(currentTemperatureLabel);
		temp = CharMatcher.digit().retainFrom(temp);
		return temp;
	}

}
