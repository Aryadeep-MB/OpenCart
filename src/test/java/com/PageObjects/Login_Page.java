package com.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.TestBase.BasePage;

public class Login_Page extends BasePage{
	
	//Contractor
	public Login_Page(WebDriver driver){

	 super(driver);//Goes to BasePage(WebDriver driver)

	}
	




@FindBy(xpath="//input[@id='input-email']")WebElement txtEmailAddress;
@FindBy(xpath="//input[@id='input-password']")WebElement txtPassword;
@FindBy(xpath="//input[@value='Login']")WebElement btnLogin;


public void setEmail(String email) {
	txtEmailAddress.sendKeys(email);
}
public void setPassword(String pass) {
	txtPassword.sendKeys(pass);
}
public void clickLogin() {
	btnLogin.click();
}

}
