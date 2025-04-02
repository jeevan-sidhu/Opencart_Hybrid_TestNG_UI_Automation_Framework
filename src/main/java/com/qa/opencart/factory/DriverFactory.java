package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppErrors;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;
import com.qa.opencart.logger.Log;

public class DriverFactory {

	private static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	private OptionsManager optionsManager;
	public static String isHighlight;

	/**
	 * This method initialize the driver based on the loaded configuration
	 * containing Browser Name and Environment
	 * 
	 * @param prop
	 * @return Local Thread copy of the Webdriver
	 */
	public WebDriver initDriver(Properties prop) {
		optionsManager = new OptionsManager(prop);
		isHighlight = prop.getProperty("highlight");
		String browser = prop.getProperty("browser").toLowerCase().trim();
		if (!Boolean.parseBoolean(prop.getProperty("remote"))) {
			Log.info("Running tests on Local with browser: " + browser);
			switch (browser) {
			case "chrome":
				tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
				break;
			case "firefox":
				tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
				break;
			case "edge":
				tldriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
				break;
			default:
				Log.error("Please pass the right browser name: " + browser);
				System.out.println("Please pass the right browser name: " + browser);
				throw new BrowserException(AppErrors.BROWSER_ERROR);
			}
		} else {
			Log.info("Running tests on Grid with browser: " + browser);
			URL hub = null;
			try {
				hub = new URI(prop.getProperty("huburl")).toURL();
			} catch (MalformedURLException | URISyntaxException e) {
				e.printStackTrace();
			}
			switch (browser) {
			case "chrome":
				tldriver.set(new RemoteWebDriver(hub, optionsManager.getChromeOptions()));
				break;
			case "firefox":
				tldriver.set(new RemoteWebDriver(hub, optionsManager.getFirefoxOptions()));
				break;
			case "edge":
				tldriver.set(new RemoteWebDriver(hub, optionsManager.getEdgeOptions()));
				break;
			default:
				Log.error("Please pass the right browser on grid: " + browser);
				throw new BrowserException(AppErrors.BROWSER_ERROR);
			}
		}

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("appurl"));
		return getDriver();
	}

	/**
	 * Get the Local Thread copy of the Webdriver
	 * 
	 * @return
	 */
	public static WebDriver getDriver() {
		return tldriver.get();
	}

	/**
	 * Load the configuration properties from .properties file
	 * 
	 * @return it returns Properties object reference (prop)
	 */
	public Properties loadConfigProperties() {
		Properties prop = new Properties();
		FileInputStream file = null;
		String env = System.getProperty("env");
		System.out.println("Running tests on " + env + " environment");
		try {
			if (env == null) {
				System.out.println("No environment provided, Running tests on QA environment");
				file = new FileInputStream(AppConstants.CONFIG_QA_FILE_PATH);
			} else {
				switch (env.toLowerCase().trim()) {
				case "qa":
					file = new FileInputStream(AppConstants.CONFIG_QA_FILE_PATH);
					break;
				case "dev":
					file = new FileInputStream(AppConstants.CONFIG_DEV_FILE_PATH);
					break;
				case "stage":
					file = new FileInputStream(AppConstants.CONFIG_STAGE_FILE_PATH);
					break;
				default:
					System.out.println("Please pass the right env name.." + env);
					throw new FrameworkException(AppErrors.ENVIRONMENT_ERROR);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static String getScreenshot(String methodName) {
		Log.info("Test: " + methodName + " Failed, Attaching Screenshot");
		TakesScreenshot ts = (TakesScreenshot) getDriver();
		File srcFile = ts.getScreenshotAs(OutputType.FILE);

		// Define the path for the screenshots folder
		String screenshotsDirPath = System.getProperty("user.dir") + "/screenshots";

		// Create the screenshots folder if it doesn't exist
		File screenshotsDir = new File(screenshotsDirPath);
		if (!screenshotsDir.exists()) {
			if (screenshotsDir.mkdirs()) {
				Log.info("Folder 'screenshots' created successfully at: " + screenshotsDirPath);
			} else {
				Log.info("Failed to create the folder 'screenshots' at: " + screenshotsDirPath);
			}
		}

		// Define the destination path for the screenshot
		String screenshotPath = screenshotsDirPath + "/" + methodName + "_" + System.currentTimeMillis() + ".png";
		File destination = new File(screenshotPath);

		// Copy the screenshot to the destination path
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return destination.getAbsolutePath();
	}

}
