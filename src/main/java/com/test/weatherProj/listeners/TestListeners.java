package com.test.weatherProj.listeners;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.test.weatherProj.factory.DriverFactory;
import static com.test.weatherProj.reports.ExtentReporterNG.getReportObject;
import static com.test.weatherProj.reports.ExtentTestManager.getTest;
import static com.test.weatherProj.reports.ExtentTestManager.startTest;



public class TestListeners extends DriverFactory implements ITestListener, IAnnotationTransformer {

    private static final Logger Log =  LogManager.getLogger(TestListeners.class);

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    	
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        getReportObject().flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
    	startTest(iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is succeed.");
        //ExtentReports log operation for passed tests.
        getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
		// TODO Auto-generated method stub
		//Screenshot
		getTest().fail(iTestResult.getThrowable());
		WebDriver driver =null;
		String testMethodName =iTestResult.getMethod().getMethodName();
		
		try {
			driver =(WebDriver)iTestResult.getTestClass().getRealClass().getDeclaredField("driver").get(iTestResult.getInstance());
		} catch(Exception e)
		{
			
		}
		try {
			getTest().addScreenCaptureFromPath(getScreenShotPath(testMethodName,driver), iTestResult.getMethod().getMethodName());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is skipped.");
        //ExtentReports log operation for skipped tests.
        getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
		
	}

}
