package com.qa.opencart.listeners;

import java.util.Calendar;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.logger.Log;


public class ExtentReportListener implements ITestListener {

	private static final String OUTPUT_FOLDER = "./reports/";
	private static final String FILE_NAME = "TestExecutionReport.html";

	private static ExtentReports extent = init();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	private static ExtentReports extentReports;
	

	private static ExtentReports init() {

		extentReports = new ExtentReports();
		ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
		reporter.config().setReportName("OpenCart Automation Test Results");
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("System", System.getProperty("os.name"));
		extentReports.setSystemInfo("Author", "Jeevan Sidhu");
		extentReports.setSystemInfo("Build", "1.1");
		extentReports.setSystemInfo("ENV NAME", System.getProperty("env"));

		return extentReports;
	}

	@Override
	public synchronized void onStart(ITestContext context) {
		Log.info("Test Suite started!");
		
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		Log.info(("Test Suite is ending!"));
		extent.flush();
		test.remove();
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String qualifiedName = result.getMethod().getQualifiedName();
		int last = qualifiedName.lastIndexOf(".");
		int mid = qualifiedName.substring(0, last).lastIndexOf(".");
		String className = qualifiedName.substring(mid + 1, last);

		Log.info(methodName + " started!");
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
				result.getMethod().getDescription());

		extentTest.assignCategory(result.getTestContext().getSuite().getName());
		extentTest.assignCategory(className);
		extentTest.assignCategory(result.getMethod().getGroups());
		test.set(extentTest);
		test.get().getModel().setStartTime(getTime(result.getStartMillis()));
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		Log.info((methodName + " passed!"));
		test.get().pass("Test passed");
		//test.get().pass(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(DriverFactory.getScreenshot(methodName), methodName).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		Log.error((result.getMethod().getMethodName() + " failed!"));
		String methodName = result.getMethod().getMethodName();
		test.get().fail("Test failed");
		test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(DriverFactory.getScreenshot(methodName), methodName).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		Log.warn((result.getMethod().getMethodName() + " skipped!"));
		String methodName = result.getMethod().getMethodName();		
		test.get().skip("Test skipped");
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		Log.info(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

}
