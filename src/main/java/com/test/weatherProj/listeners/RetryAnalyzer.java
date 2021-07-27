package com.test.weatherProj.listeners;

import static com.test.weatherProj.reports.ExtentTestManager.getTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.test.weatherProj.factory.DriverFactory;

public class RetryAnalyzer implements IRetryAnalyzer {

	private int count = 0;
	private int retryLimit = 1;

	@Override
	public boolean retry(ITestResult iTestResult) {
		if(System.getProperty("retryLimit")!=null)
			retryLimit=Integer.parseInt(System.getProperty("retryLimit"));
		if (!iTestResult.isSuccess()) {
			if (count < retryLimit) {
				count++;
				iTestResult.setStatus(ITestResult.FAILURE);
				extendReportsFailOperations(iTestResult);
				return true;
			}
		} else {
			iTestResult.setStatus(ITestResult.SUCCESS);
		}
		return false;
	}

	public void extendReportsFailOperations(ITestResult iTestResult) {
		getTest().fail(iTestResult.getThrowable());
		WebDriver driver = null;
		String testMethodName = iTestResult.getMethod().getMethodName();

		try {
			driver = (WebDriver) iTestResult.getTestClass().getRealClass().getDeclaredField("driver")
					.get(iTestResult.getInstance());
		} catch (Exception e) {

		}
		try {
			getTest().addScreenCaptureFromPath(DriverFactory.getScreenShotPath(testMethodName, driver),
					iTestResult.getMethod().getMethodName());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}