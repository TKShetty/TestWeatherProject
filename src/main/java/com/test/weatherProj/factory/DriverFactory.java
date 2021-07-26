package com.test.weatherProj.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Driver Factory class to initialize Driver and invoke required browser
 * 
 * @author TShetty
 *
 */
public class DriverFactory {

	private Properties prop;
	public OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> webDriverThread = new ThreadLocal<>();

	/**
	 * Initialize the driver on the basis of given browser
	 * 
	 * @param prop
	 * @return
	 */
	public WebDriver intializeDriver(Properties prop) {

		String browserName = prop.getProperty("browser").trim();
		String browserVersion = prop.getProperty("browserversion").trim();
		optionsManager = new OptionsManager(prop);
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("chrome", browserVersion);
			} else {
				webDriverThread.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}

		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("firefox", browserVersion);
			} else {
				webDriverThread.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
		} else {
			System.out.println("Please pass the right brower: " + browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();

	}

	private void init_remoteDriver(String browser, String browserVersion) {

		System.out.println("Running test on grid server: " + browser + " version: " + browserVersion);

		if (browser.equals("chrome")) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability("browserName", "chrome");
			cap.setCapability("browserVersion", browserVersion);
			try {
				webDriverThread.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else if (browser.equals("firefox")) {
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability("browserName", "firefox");
			cap.setCapability("browserVersion", browserVersion);
			try {
				webDriverThread.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

	}

	public static synchronized WebDriver getDriver() {
		return webDriverThread.get();
	}

	/**
	 * Initialize the prop from config file
	 * 
	 * @return
	 */
	public Properties initializeProperties() {
		prop = new Properties();
		FileInputStream ip = null;
		try {
			ip = new FileInputStream("./src/test/resources/config/config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * Capture screenshot
	 * 
	 * @param testCaseName
	 * @param driver
	 * @return
	 * @throws IOException
	 */
	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		String encodedBase64 = null;
		FileInputStream fileInputStreamReader = null;
		try {
			fileInputStreamReader = new FileInputStream(sourceFile);
			byte[] bytes = new byte[(int) sourceFile.length()];
			fileInputStreamReader.read(bytes);
			encodedBase64 = new String(Base64.encodeBase64(bytes));

			String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
			FileUtils.copyFile(sourceFile, new File(destinationFile));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "data:image/png;base64," + encodedBase64;
	}

}
