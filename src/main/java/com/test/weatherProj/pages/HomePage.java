package com.test.weatherProj.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.test.weatherProj.utils.CustomException;
import com.test.weatherProj.utils.ElementUtil;

public class HomePage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By searchTxtBox = By.name("query");
	private By searchResult = By.xpath("//div[@class='results-container']//div[contains(@class,'search-result')]");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	public WeatherForecastPage searchCity(final String cityName) throws CustomException {
		elementUtil.doSendKeys(searchTxtBox, cityName);
		try {
			elementUtil.waitForElementsToBeVisible(searchResult, 15);
		} catch (Exception e) {
			throw new CustomException("Search results are not getting displayed for following city " + cityName);
		}
		selectCityFrmSearchResultDD(cityName);
		return new WeatherForecastPage(driver);
	}

	private void selectCityFrmSearchResultDD(final String cityName) throws CustomException {
		Boolean cityFound = false;
		List<WebElement> searchResults = elementUtil.doGetElements(searchResult);
		for (WebElement searchResult : searchResults) {
			String searchResultesultCity = searchResult.getText().split(",")[0];
			if (searchResultesultCity.equalsIgnoreCase(cityName)) {
				searchResult.click();
				cityFound = true;
				break;
			}
		}
		if (!cityFound)
			throw new CustomException("Unable to find city in search results");
	}
}
