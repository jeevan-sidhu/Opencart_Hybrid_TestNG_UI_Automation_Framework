package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utilities.ElementUtil;
import com.qa.opencart.utilities.TimeUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil elUtil;

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elUtil = new ElementUtil(driver);
	}
	
	public String getAccPageTitle() {
		String title = elUtil.waitForTitleToBe(AppConstants.ACCOUNTS_PAGE_TITLE, TimeUtil.DEFAULT_TIME);
		System.out.println("Acc page title : " + title);
		return title;
	}

}
