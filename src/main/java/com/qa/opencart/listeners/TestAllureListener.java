package com.qa.opencart.listeners;


import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.logger.Log;


public class TestAllureListener implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	
	// Text attachments for Allure
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	// Text attachments for Allure
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}

	// HTML attachments for Allure
	@Attachment(value = "{0}", type = "text/html")
	public static String attachHtml(String html) {
		return html;
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		Log.info("I am in onStart method " + iTestContext.getName());
		//iTestContext.setAttribute("WebDriver", BasePage.getDriver());
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		Log.info("I am in onFinish method " + iTestContext.getName());
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		Log.info("Test " + getTestMethodName(iTestResult) + " started");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		Log.info("Test " + getTestMethodName(iTestResult) + " succeed");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		Log.error("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
		Object testClass = iTestResult.getInstance();
		// Allure ScreenShotRobot and SaveTestLog
		if (DriverFactory.getDriver() instanceof WebDriver) {
			Log.info("Screenshot captured for test case:" + getTestMethodName(iTestResult));
			saveScreenshotPNG(DriverFactory.getDriver());
		}
		// Save a log on allure.
		saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");		
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		Log.warn("Test " + getTestMethodName(iTestResult) + " skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		Log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}

}


