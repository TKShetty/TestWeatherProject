package com.test.weatherProj.pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.google.common.base.CharMatcher;
import com.test.weatherProj.utils.CustomException;
import com.test.weatherProj.utils.ElementUtil;

public class WeatherForecastPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By currentTemperatureLabel = By.xpath("//div[@class='cur-con-weather-card__body']//div[@class='temp']");

	public WeatherForecastPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	public String getCurrentTemperature() throws CustomException {
		String temp = elementUtil.doGetText(currentTemperatureLabel);
		if (StringUtils.isAllBlank(temp)) {
			throw new CustomException("Empty temperature is getting displayed for the selected city");
		}
		temp = CharMatcher.digit().retainFrom(temp);
		return temp;
	}

}
