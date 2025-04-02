package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utilities.ElementUtil;

import io.qameta.allure.Step;

public class ResultsPage {

	private WebDriver driver;
	private ElementUtil elUtil;

	private By searchHeader = By.cssSelector("div#content h2");
	private By results = By.cssSelector("div.product-thumb");

	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		elUtil = new ElementUtil(driver);
	}

	@Step("getting search header")
	public String getSearchHeader() {
		String searchHeaderValue = elUtil.waitForElementVisible(searchHeader, AppConstants.DEFAULT_SHORT_TIME_OUT)
				.getText();
		return searchHeaderValue;
	}

	@Step("getting search result count")
	public int getSearchResultsCount() {
		int resultCount = elUtil.waitForElementsVisible(results, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("search result count ===> " + resultCount);
		return resultCount;
	}

	@Step("Selecting product: {0}")
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("selecting product: " + productName);
		elUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}

}
