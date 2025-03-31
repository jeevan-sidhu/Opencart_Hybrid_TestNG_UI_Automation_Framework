package com.qa.opencart.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementUtil {
	
	private WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}
	
	public String doGetTitle() {
		return driver.getTitle();
	}
	
	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

}
