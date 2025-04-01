package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utilities.ElementUtil;
import com.qa.opencart.utilities.TimeUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil elUtil;
	
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elUtil = new ElementUtil(driver);
	}
	
	public String getPageTitle() {
		String title = elUtil.waitForTitleToBe(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_TIME);
		System.out.println("login page title : " + title);
		return title;
	}
	
	public AccountsPage doLogin(String username, String pwd) {
		elUtil.doSendKeys(emailId, username, TimeUtil.DEFAULT_MEDIUM_TIME);
		elUtil.doSendKeys(password, pwd);
		elUtil.doClick(loginBtn);
		return new AccountsPage(driver);

	}

}
