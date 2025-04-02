package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utilities.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil elUtil;
	
	private By logoutLink = By.linkText("Logout");
	private By headers = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elUtil = new ElementUtil(driver);
	}
	
	@Step("getting Account page title value")
	public String getAccountsPageTitle() {
		String title = elUtil.waitForTitleContainsAndReturn(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIME_OUT);
		System.out.println("Accounts page title: " + title);
		return title;
	}
	
	@Step("getting Account page URL value")
	public String getAccountPageUrl() {
		String url = elUtil.waitForURLContainsAndReturn(AppConstants.ACC_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_TIME_OUT);
		System.out.println("Account page url : " + url);
		return url;
	}
	
	@Step("checking logout link exist on the account page")
	public boolean isLogoutLinkExist() {
		return elUtil.isElementDisplayed(logoutLink);		
	}
	
	@Step("getting count of Account page headers")
	public int getTotalAccountsPageHeader() {
		return elUtil.waitForElementsVisible(headers, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
	}
	
	@Step("getting list of headers on Account page")
	public List<String> getAccPageHeaders() {
		List<WebElement> headersList = elUtil.waitForElementsVisible(headers, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		List<String> headersValueList = new ArrayList<String>();
		for(WebElement e : headersList) {
			String header = e.getText();
			headersValueList.add(header);
		}
		return headersValueList;
	}
	
	@Step("Searching product: {0} and navigating to product results page")
	public ResultsPage doSearch(String searchKey) {
		System.out.println("Search Key ==>" + searchKey);
		WebElement searchEle = elUtil.waitForElementVisible(search, AppConstants.DEFAULT_SHORT_TIME_OUT);
		elUtil.doSendKeys(searchEle, searchKey);
		elUtil.doClick(searchIcon);
		return new ResultsPage(driver);
	}
	

}
