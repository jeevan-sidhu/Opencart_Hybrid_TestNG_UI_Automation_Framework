package com.qa.opencart.tests;

import java.util.List;

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

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Severity(SeverityLevel.MINOR)
	@Description("Accounts page title test")
	@Test
	public void accPageTitleTest() {
		String actTitle = accPage.getAccountsPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("Accounts page URL test")
	@Test
	public void accPageUrlTest() {
		Assert.assertTrue(accPage.getAccountPageUrl().contains(AppConstants.ACC_PAGE_FRACTION_URL));
	}

	@Severity(SeverityLevel.NORMAL)
	@Description("Accounts page logout link exist test")
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	@Severity(SeverityLevel.TRIVIAL)
	@Description("Account page headers count test")
	@Test
	public void accPageHeadersCountTest() {
		Assert.assertEquals(accPage.getTotalAccountsPageHeader(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);

	}

	@Severity(SeverityLevel.TRIVIAL)
	@Description("Account page headers name test")
	@Test
	public void accPageHeadersTest() {
		List<String> actualHeadersList = accPage.getAccPageHeaders();
		Assert.assertEquals(actualHeadersList, AppConstants.EXPECTED_ACC_PAGE_HEADERS_LIST);
	}
	
	
	@DataProvider
	public Object[][] getSearchKey() {
		return new Object[][] {
			{"macbook", 3},
			{"imac", 1},
			{"samsung", 2},
			
		};
	}

	@Severity(SeverityLevel.NORMAL)
	@Description("Searching product and validating result count")
	@Test(dataProvider = "getSearchKey")
	public void searchCountTest(String searchKey, int searchCount) {
		resultsPage = accPage.doSearch(searchKey);
		Assert.assertEquals(resultsPage.getSearchResultsCount(), searchCount);
	}
	
	
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"macbook", "MacBook Air"},
			{"imac", "iMac"},
			{"samsung", "Samsung SyncMaster 941BW"},
			{"samsung", "Samsung Galaxy Tab 10.1"}
		};
	}
	
	
	@DataProvider
	public Object[][] getSearcExcelData() {
		return ExcelUtil.getTestData(AppConstants.SEARCH_SHEET_NAME);
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Searching product and validating product name")
	@Test(dataProvider = "getSearcExcelData")
	public void searchTest(String searchKey, String productName) {
		resultsPage = accPage.doSearch(searchKey);
		productInfoPage = resultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductHeader(), productName);
	}

}
