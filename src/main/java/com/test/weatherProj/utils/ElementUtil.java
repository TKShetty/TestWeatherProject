package com.test.weatherProj.utils;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ElementUtil {

	public WebDriver driver;
	private static Logger log = LogManager.getLogger(ElementUtil.class.getName());

	public ElementUtil(WebDriver driver) {
		this.driver = driver;

	}

	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		log.info(locator+" element located");
		return element;
	}

	public List<WebElement> doGetElements(By locator) {
		List<WebElement> elements = driver.findElements(locator);
		log.info(locator+" element located");
		return elements;
	}

	public void doSendKeys(By locator, String value) {
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
		log.info("Clicked On "+locator);
	}

	public String doGetText(By locator) {
		getElement(locator).isDisplayed();
		String text=getElement(locator).getText();
		log.info("Text for element located"+locator+": "+text);
		return text;
	}

	public List<WebElement> waitForElementsToBeVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

}
