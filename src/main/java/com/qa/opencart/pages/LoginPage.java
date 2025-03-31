package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.utilities.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil elUtil;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elUtil = new ElementUtil(driver);
	}
	
	public String getPageTitle() {
		return elUtil.doGetTitle();
	}

}
