package com.test.weatherProj.listeners;

import static com.test.weatherProj.reports.ExtentTestManager.getTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.test.weatherProj.factory.DriverFactory;

public class RetryAnalyzer implements IRetryAnalyzer {

	private int count = 0;
	private int retryLimit = 0;
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.IRetryAnalyzer#retry(org.testng.ITestResult)
	 * 
	 * This method decides how many times a test needs to be rerun. TestNg will call
	 * this method every time a test fails. So we can put some code in here to
	 * decide when to rerun the test.
	 * 
	 * Note: This method will return true if a tests needs to be retried and false
	 * it not.
	 *
	 */

	@Override
	public boolean retry(ITestResult iTestResult) {
		if (!iTestResult.isSuccess()) {
			if (count < retryLimit) {
				count++;
				iTestResult.setStatus(ITestResult.FAILURE);
				// extendReportsFailOperations(iTestResult);
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