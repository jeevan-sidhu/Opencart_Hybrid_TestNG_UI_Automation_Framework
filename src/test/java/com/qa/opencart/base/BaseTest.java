package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;

public class BaseTest {
	
	private WebDriver driver;
	private DriverFactory df;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(@Optional String browser) {
		df = new DriverFactory();
		prop = df.loadConfigProperties();
		if (browser!=null) {
			prop.setProperty("browser", browser);
		}
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		accPage = new AccountsPage(driver);
	}
	
	@AfterTest
	public void tearDown() {
		if(DriverFactory.getDriver()!=null) {
			DriverFactory.getDriver().quit();
		}
	}

}
