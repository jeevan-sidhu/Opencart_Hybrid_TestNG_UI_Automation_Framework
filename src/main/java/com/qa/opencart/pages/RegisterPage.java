package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utilities.ElementUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil elUtil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private By successMessg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		elUtil = new ElementUtil(driver);
	}

	public boolean userRegisteration(String firstName, String lastName, String email, String telephone, String password, String subscribe) {

		elUtil.waitForElementVisible(this.firstName, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(firstName);

		elUtil.doSendKeys(this.lastName, lastName);
		elUtil.doSendKeys(this.email, email);
		elUtil.doSendKeys(this.telephone, telephone);
		elUtil.doSendKeys(this.password, password);
		elUtil.doSendKeys(this.confirmpassword, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			elUtil.doClick(subscribeYes);
		} else {
			elUtil.doClick(subscribeNo);
		}

		elUtil.doClick(agreeCheckBox);
		elUtil.doClick(continueButton);

		String successMesg = elUtil.waitForElementVisible(successMessg, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
		System.out.println(successMesg);
		

		if (successMesg.contains(AppConstants.USER_REGISTER_SUCCESS_MESSG)) {
			elUtil.doClick(logoutLink);
			elUtil.doClick(registerLink);
			return true;
		} else {
			return false;
		}

	}

}
