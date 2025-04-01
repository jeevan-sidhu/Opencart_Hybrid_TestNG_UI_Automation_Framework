package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppErrors;

public class LoginPageTest extends BaseTest {
	
	@Test()
	public void loginPageTitleTest() {
		Assert.assertEquals(loginPage.getLoginPageTitle(), "Account Login");
	}
	
	@Test()
	public void loginPageUrlTest() {
		Assert.assertTrue(loginPage.getLoginPageUrl().contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}
	
	@Test
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test()
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccountsPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE, AppErrors.TITLE_NOT_FOUND);
	}

}
