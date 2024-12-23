package com.PageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.TestBase.BasePage;

import io.reactivex.rxjava3.functions.Action;

public class Registration_page extends BasePage{
	
	

	public Registration_page (WebDriver driver)

	{

	super(driver);

	}

	@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstname;

	@FindBy(xpath="//input[@id='input-lastname' ]")WebElement txtLasttname;

	@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;

	@FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelephone;

	@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;

	@FindBy(xpath="//input[@id='input-confirm']") WebElement txtConfirmPassword;
	
	@FindBy (xpath="//input[@name='agree']")WebElement chkdPolicy;

	@FindBy(xpath="//input[@value='Continue']")WebElement btnContinue;

	@FindBy(xpath = "//h1 [normalize-space()= 'Your Account Has Been Created!']")WebElement msgConfirmation;

	public void setFirstName (String fname) {

	txtFirstname.sendKeys(fname);

	}

	public void setLastName (String lname) {

	txtLasttname.sendKeys(lname);

	}

	public void setEmail(String email) {

	txtEmail.sendKeys (email);

	}

	public void setTelephone (String tel) {

	txtTelephone.sendKeys (tel);

	}

	public void setPassword(String pwd) {


	txtPassword.sendKeys(pwd);

	}

	public void setConfirmPassword(String pwd) {

	txtConfirmPassword.sendKeys(pwd);

	}

	public void setPrivacyPolicy() {

	chkdPolicy.click();

	}

	public void clickContinue() {
    //[ IF click is not working ]
		
	//sol-1 
	btnContinue.click();
	
	//sol-2
//	btnContinue.submit();
	
	//sol-3
//	Actions act =new Actions(driver);
//	act.moveToElement(btnContinue).click().perform();
	
	//sol-4
//	btnContinue.sendKeys(Keys.RETURN);
	
	//sol-5
//	JavascriptExecutor js =(JavascriptExecutor) driver;
//	js.executeScript("argument[0].click();",btnContinue);
	
	//sol-6
//	WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
//	mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
	
	
}
	//IT is used to capture ConfirmMsg and return it
	
	public String getConfirmationMsg() {
		try {
		return(msgConfirmation.getText());
		}
		catch(Exception e) {
			return(e.getMessage());
		}
	}
	}
