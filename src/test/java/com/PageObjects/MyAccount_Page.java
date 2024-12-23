package com.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.TestBase.BasePage;

public class MyAccount_Page extends BasePage {

	public MyAccount_Page(WebDriver driver) {
		super(driver);
	}
	


@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")WebElement lnkLogout;

@FindBy(xpath="//h2[normalize-space()='My Account']")WebElement msgHeading;


public void clickLogout() {
	lnkLogout.click();
}

//This is not validation it's just cheacking msg is displayed are not ,not checking correct or not

public boolean ismyaccountexixts() {
	try {
	return msgHeading.isDisplayed();
}
	catch(Exception e) {
		return false;
	}

}
}
