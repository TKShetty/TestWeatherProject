package com.test.weatherProj.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	private static final ExtentReports extentReports = new ExtentReports();

	public synchronized static ExtentReports getReportObject() {

		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("QA", "Taranath Shetty");
		return extentReports;

	}
}
