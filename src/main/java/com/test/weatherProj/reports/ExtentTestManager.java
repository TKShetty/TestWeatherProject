package com.test.weatherProj.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
	static ExtentReports extent = ExtentReporterNG.getExtentReports();

	public static synchronized ExtentTest getTest() {
		return extentTestMap.get((int) Thread.currentThread().getId());
	}

	public static synchronized ExtentTest startTest(String testName) {
		ExtentTest test = extent.createTest(testName);
		extentTestMap.put((int) Thread.currentThread().getId(), test);
		return test;
	}
}