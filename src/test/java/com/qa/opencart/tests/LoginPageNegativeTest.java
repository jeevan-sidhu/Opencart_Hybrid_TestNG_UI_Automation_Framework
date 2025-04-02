package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utilities.CSVUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageNegativeTest extends BaseTest{
	
	
	@DataProvider
	public Object[][] invalidLoginData() {
		return CSVUtil.csvData("login");
	}
		
	@Severity(SeverityLevel.CRITICAL)
	@Description("Invalid user login test")
	@Test(dataProvider = "invalidLoginData")
	public void invalidLoginTest(String username, String password) {
		Assert.assertTrue(loginPage.doInvalidLogin(username, password));
	}
	

}
