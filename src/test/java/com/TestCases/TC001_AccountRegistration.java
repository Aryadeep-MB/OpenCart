 
package com.TestCases;


import org.testng.Assert;
import org.testng.annotations.Test;
import com.PageObjects.Home_Page;
import com.PageObjects.Registration_page;
import com.TestBase.BaseMethods;

public class TC001_AccountRegistration extends BaseMethods  {
	

	
	@Test(groups="Regression")
	public void verify_account_registration() {
		
		logger.info("====Starting======");
		
		try {
		//To navigate to register page
		Home_Page homeP=new Home_Page(driver);//PageObject 1
		homeP.clickMyAccount();
		homeP.clickRegister();
		
		logger.info("====Click on my account======");
		
		//To register new account
		Registration_page registerP =new Registration_page(driver);//PageObject 2
		
		logger.info("====Click on register======");
		
		
		logger.info("====entering valid information======");
		
		
		registerP.setFirstName(randomString().toUpperCase());
		registerP.setLastName("MB");
		
//		registerP.setEmail("ary123@gmail.com");
		registerP.setEmail(randomString()+"@gmail.com");
		
//		registerP.setTelephone("9447565656");
		registerP.setTelephone(randomnumber());
		
		String password=randompassword();
		registerP.setPassword(password);
		registerP.setConfirmPassword(password);
		
		registerP.setPrivacyPolicy();
		registerP.clickContinue();
		
		
		logger.info("====Validating confirm msg======");
		
		
		String confmsg=registerP.getConfirmationMsg();
		//Add validation
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e){
			logger.error("Test failed.....");//Error level
			logger.debug("Debug logs.....");//Debug level
			Assert.fail();
			
		}
		logger.info("===finished===");
	}
	
//
	
	
	


}
