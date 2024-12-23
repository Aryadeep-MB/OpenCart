package com.PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.TestBase.BasePage;

public class Home_Page extends BasePage {

	//Contractor
	public Home_Page (WebDriver driver){

	 super(driver);//Goes to BasePage(WebDriver driver)

	}



	//Locaters
	@FindBy(xpath="//span[normalize-space()='My Account']")WebElement InkMyaccount;
	

	@FindBy(xpath="//a[normalize-space()='Register']")WebElement InkRegister;
	
	@FindBy(xpath="//a[normalize-space()='Login']")WebElement InkLogin;


	//Actions
	public void clickMyAccount()

	{
	InkMyaccount.click();
	}

	public void clickRegister()

	{
	InkRegister.click();

	}
	public void clickLogin() {
		InkLogin.click();
	}



}
