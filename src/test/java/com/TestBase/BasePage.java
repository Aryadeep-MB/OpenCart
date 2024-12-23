//[ Base class for all PageObject class]

package com.TestBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	
	public WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);//Used to use @FindBy annontation /Using factory class here
		
	}
	
	

}
