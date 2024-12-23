package com.TestCases;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.PageObjects.Home_Page;
import com.PageObjects.Login_Page;
import com.PageObjects.MyAccount_Page;
import com.TestBase.BaseMethods;

public class TC002_AccountLogin extends BaseMethods {

    private Properties prop; // Declare the 'prop' variable

    @Test(groups ={"Sanity","Master"})
    public void verify_login() throws IOException {

        // Load configuration properties
        try (FileReader file = new FileReader("src/test/resources/config_properties")) {
            prop = new Properties();
            prop.load(file);
        }

        logger.info("====Starting Test: verify_login====");

        try {
            // HOME PAGE OPERATIONS
            Home_Page home = new Home_Page(driver);
            home.clickMyAccount();
            home.clickLogin();
            logger.info("Navigated to the login page.");

            // LOGIN OPERATIONS
            Login_Page login = new Login_Page(driver);
            logger.info("Entering email.");
            login.setEmail(prop.getProperty("Email"));
            logger.info("Entering password.");
            login.setPassword(prop.getProperty("Password"));
            login.clickLogin();
            logger.info("Clicked login button.");

            // ACCOUNT VALIDATION
            MyAccount_Page acc = new MyAccount_Page(driver);
            boolean loginMsg = acc.ismyaccountexixts();

            // Validate the login operation
            Assert.assertTrue(loginMsg, "Login failed. My Account page is not visible.");
            logger.info("Login test passed successfully.");
        } catch (Exception e) {
            logger.error("Test failed due to an exception: ", e);
            Assert.fail("Exception occurred during login test: " + e.getMessage());
        }

        logger.info("====Test Finished: verify_login====");
    }
}


//Data is valid -login sucess - test PASS -logout
//Data is valid - login unsucsses - test FAIL

//Data is invalid -login sucess - test PASS -logout
//Data is invalid - login unsucsses - test FAIL