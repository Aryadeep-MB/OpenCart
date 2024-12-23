package com.TestCases;

import java.time.Duration;

//Data is valid -login sucess - test PASS -logout
//Data is valid - login unsucsses - test FAIL

//Data is invalid -login sucess - test PASS -logout
//Data is invalid - login unsucsses - test FAIL

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.PageObjects.Home_Page;
import com.PageObjects.Login_Page;
import com.PageObjects.MyAccount_Page;
import com.TestBase.BasePage;
import com.Utilities.Data_Provider;

public class TC003_Login_DDT extends BasePage {



	//Use try catch if you used logger info()
// [==========    ?it is not required it is there in base class but here not working?	
	public TC003_Login_DDT() {
		super(null);
		// TODO Auto-generated constructor stub
	}
	
	WebDriver driver;
	
	@BeforeClass
	public void setup() {
		driver=new ChromeDriver();
		driver.manage().deleteAllCookies();//To delete cookies 
		
		driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");//reading appurl from property file
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
// ==============]	

	@Test(dataProvider = "LoginData", dataProviderClass = Data_Provider.class,groups="Datadriven") // DataProvider in another package
	public void verify_loginDDT(String email, String pwd, String exp) {
		
	    // HomePage
	    Home_Page hp = new Home_Page(driver);
	    hp.clickMyAccount();
	    hp.clickLogin();

	    // Login
	    Login_Page lp = new Login_Page(driver);
	    lp.setEmail(email);
	    lp.setPassword(pwd);
	    lp.clickLogin();

	    // MyAccount
	    MyAccount_Page macc = new MyAccount_Page(driver);
	    boolean targetPage = macc.ismyaccountexixts();

	    
	    // Validation conditions
	    
	       //Data is valid -login sucess - test PASS -logout
	    if (exp.equalsIgnoreCase("Valid")) {
	        if (targetPage==true) {
	            macc.clickLogout();
	            Assert.assertTrue(true, "Login successful with valid data.");
	        }
	        //Data is valid - login unsucsses - test FAIL
	        else {
	            Assert.assertTrue(false);
	        }
	    } 
	    
	  //Data is invalid -login sucess - test PASS -logout
	    else if (exp.equalsIgnoreCase("Invalid")) {
	        if (targetPage==true) {
	            macc.clickLogout();
	            Assert.fail("Login successful with invalid data.");
	        } else {
	            Assert.assertTrue(true, "Login failed with invalid data as expected.");
	        }
	    } else {
	    	  Assert.assertTrue(true);
	    }
	}
	
	

}




