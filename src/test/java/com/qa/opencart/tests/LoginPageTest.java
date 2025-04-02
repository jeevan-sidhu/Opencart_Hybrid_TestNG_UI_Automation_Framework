package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppErrors;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageTest extends BaseTest {
	
	@Severity(SeverityLevel.MINOR)
	@Description("login page title test")
	@Test()
	public void loginPageTitleTest() {
		Assert.assertEquals(loginPage.getLoginPageTitle(), "Account Login");
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("login page URL test")
	@Test()
	public void loginPageUrlTest() {
		Assert.assertTrue(loginPage.getLoginPageUrl().contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("login page Forgot password link exist test")
	@Test
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Description("Valid user login test")
	@Test()
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccountsPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE, AppErrors.TITLE_NOT_FOUND);
	}

}
