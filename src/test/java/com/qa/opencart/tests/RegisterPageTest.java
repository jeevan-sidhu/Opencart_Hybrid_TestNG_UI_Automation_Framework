package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utilities.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void regSetup() {
		registerPage = loginPage.navigateToRegisterPage();

	}
	
	
	public String getRandomEmail() {
		return "uiautomation"+System.currentTimeMillis()+"@open.com";
	}
	
	
	@DataProvider
	public Object[][] getRegData() {
		return ExcelUtil.getTestData(AppConstants.REG_SHEET_NAME);
	}
	
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("User registration test")
	@Test(dataProvider = "getRegData")
	public void userRegisterTest(String firstname, String lastname, String telephone, String password, String subscribe) {
		Assert.assertTrue(registerPage.userRegisteration(firstname, lastname, getRandomEmail(), telephone, password, subscribe));

	}

}
