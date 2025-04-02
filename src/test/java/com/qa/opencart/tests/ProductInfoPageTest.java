package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class ProductInfoPageTest extends BaseTest{
	
	
	@BeforeClass
	public void productInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("Searched product header test")
	@Test
	public void productHeaderTest() {
		resultsPage = accPage.doSearch("macbook");
		productInfoPage = resultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeader(), "MacBook Pro");
	}
		
	@Severity(SeverityLevel.NORMAL)
	@Description("Searched product info test")
	@Test
	public void productInfoTest() {
		
		SoftAssert softAssert = new SoftAssert();
		resultsPage = accPage.doSearch("macbook");
		productInfoPage = resultsPage.selectProduct("MacBook Pro");
		Map<String, String> actProductDataMap = productInfoPage.getProductData();
		
		softAssert.assertEquals(actProductDataMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductDataMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductDataMap.get("Reward Points"), "800");
		softAssert.assertEquals(actProductDataMap.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(actProductDataMap.get("productprice"), "$2,000.00");
		softAssert.assertEquals(actProductDataMap.get("extaxprice"), "$2,000.00");
		softAssert.assertAll();
	}
	
	
	@DataProvider
	public Object[][] getProductImagesCountData() {
		return new Object[][] {
			{"macbook", "MacBook Pro", 4},
			{"imac", "iMac", 3},
			{"samsung", "Samsung SyncMaster 941BW", 1},
			{"samsung", "Samsung Galaxy Tab 10.1", 7},
			{"canon", "Canon EOS 5D", 3}
		};
	}
	
	@Severity(SeverityLevel.MINOR)
	@Description("Searched product images count test")
	@Test(dataProvider = "getProductImagesCountData")
	public void productImagesCountTest(String searchKey, String productName, int imagesCount) {
		resultsPage = accPage.doSearch(searchKey);
		productInfoPage = resultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), imagesCount);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
