//[ Base class for TC001_AccountRegistration]

package com.TestBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import java.net.URL;

public class BaseMethods {
	
	public static WebDriver driver;
	
	//To update log.xml file - log4j2
	public Logger logger; 
	
	//To get value from properties file
	public Properties prop;
	
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"OS","browser"})
	public void setup(String OS ,String br) throws IOException {
		
		//Loading config_property value
		FileReader file =new FileReader("C:\\Users\\HP\\Desktop\\AUTOMATION TESTING\\OpenCart_Project\\src\\test\\resources\\config_properties");
		prop=new Properties();
		prop.load(file);
		
		
		
		
		//=========this is the statment used to load log4j2 file======
		logger=LogManager.getLogger(this.getClass());
		//this.getClass() used because to get dynamical class name 
		
		
		System.setProperty("log4j.configurationFile", "path_to_log4j2.xml");
		
		
		// =========THIS CODE IS FOR SELENIUM GRID REMOTE EXICUTION========
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities cap = new DesiredCapabilities();
			
			//os
			if(OS.equalsIgnoreCase("Windows")) {
				cap.setPlatform(Platform.WIN11);
			}
			else if(OS.equalsIgnoreCase("Mac")) {
				cap.setPlatform(Platform.MAC);
			}
			else if(OS.equalsIgnoreCase("Linux")) {
				cap.setPlatform(Platform.LINUX);
			}
			else {
				System.out.println("Invalid OS");
				return;
			}
			//browser
			switch(br.toLowerCase()) {
			case "chrome":cap.setBrowserName("chrome");
			break;
			case "edge":cap.setBrowserName("MicrosoftEdge");
			break;
			case "firefox":cap.setBrowserName("firefox");
			break;
			default:System.out.println("not matching browser"); return;
			}
			
			//To trigger the gid envirormrnt
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		}

		
		// =========THIS CODE IS FOR SELENIUM LOCAL EXICUTION========
		if(prop.getProperty("execution_env").equalsIgnoreCase("local")) {
		 switch(br.toLowerCase())
		   {
			   case "chrome": driver=new ChromeDriver();break;
			   case "edge": driver=new EdgeDriver();break;
			   case "firefox": driver=new FirefoxDriver();break;
			   default:System.out.println("invalid");return;
			  //if browser in invalid whole code will not exicute exit from code so return
		   }
		}
		
		
		driver.manage().deleteAllCookies();//To delete cookies 
		
		driver.get(prop.getProperty("appURL1"));//reading appurl from property file
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		

	}
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void teardown() {
		driver.close();
		
	}
	
	//[ Random String ]
	public String randomString(){
		String genatated=RandomStringUtils.randomAlphabetic(5);//5=size of the string
		return genatated;
	}
	
	//[ Random Number ]
	public String randomnumber(){
		String genatated=RandomStringUtils.randomNumeric(10);//5=size of the string
		return genatated;
	}
	
	//[ Random Password ]
	public String randompassword(){
		String genatated=RandomStringUtils.randomAlphanumeric(5);//5=size of the string
		return genatated;
	}
	
	//SCREENSHORT METHOID
	public String captureScreen(String tname) throws IOException {
	    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

	    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

	    String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
	    File targetFile = new File(targetFilePath);

	    sourceFile.renameTo(targetFile);

	    return targetFilePath;
	}
}
