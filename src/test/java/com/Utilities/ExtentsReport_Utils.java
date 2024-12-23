package com.Utilities;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.TestBase.*;
import org.testng.ITestContext;
import org.testng.ITestListener;

import org.testng.ITestResult;
import com.aventstack.extentreports. ExtentReports;
import com.aventstack.extentreports. ExtentTest;
import com.aventstack.extentreports. Status;
import com.aventstack.extentreports.reporter. ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentsReport_Utils implements ITestListener{
	public ExtentSparkReporter sparkReporter;  // UI of the report
	public ExtentReports extent;              //populate common info on the report
	public ExtentTest test;                   // creating test case entries in the report and update status of the test methods
	String repName;
	
	
	public void onStart(ITestContext testContext) {
		
		

		//IT IS USED TO CREATE REPORT NAME AND FILL THE REPORT WITH CURRENT DATE
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName ="Test-Report-"+timeStamp +".html";
		sparkReporter =new ExtentSparkReporter(".\\Reports\\"+repName);
		
    //To make dynamic file name = by using "time stamp"
	sparkReporter.config().setDocumentTitle("OpenCart Automation Report");  // Title of report
	sparkReporter.config().setReportName("Functional Testing");            // name of the report
	sparkReporter.config().setTheme (Theme.DARK);                          //also use STANDARD
	
	extent=new ExtentReports();
	
	extent.attachReporter (sparkReporter);
	extent.setSystemInfo("Application", "opencart");
	extent.setSystemInfo("Module", "Admin");
	extent.setSystemInfo("Submodule", "Customers");
	extent.setSystemInfo("Username Name", System.getProperty("user.name"));
	extent.setSystemInfo("Environment", "QA");
	

	//OS and BROWSER will get from .xml file
	String os =testContext.getCurrentXmlTest().getParameter("OS");
	extent.setSystemInfo("Operating System", os);
	
	String browser =testContext.getCurrentXmlTest().getParameter("browser");
	extent.setSystemInfo("Browser", browser);
	
	
	//GROUPS included are get from .xml file
	List<String> includeGroups = testContext.getCurrentXmlTest().getIncludedGroups();
	if(!includeGroups.isEmpty()) { //if empty the skip this no else part
		extent.setSystemInfo("Groups", includeGroups.toString());
		
	}

	}

	public void onTestSuccess (ITestResult result) { //result stores all result of exicution

	test =extent.createTest(result.getTestClass().getName()); // create a new entry in the report by getting class name 
	
	test.assignCategory(result.getMethod().getGroups());       //To display grp in class
	test.log(Status.PASS,result.getName()+"Got successfully executed"); // update status p/f/s

	}

	public void onTestFailure (ITestResult result) {

		test =extent.createTest(result.getTestClass().getName()); // create a new entry in the report
		test.assignCategory(result.getMethod().getGroups());  
		
	    test.log(Status.FAIL,result.getTestName()+"Got failed"); 
	    test.log(Status.INFO,result.getThrowable().getMessage());

	
	try {
		String imgPath=new BaseMethods().captureScreen(result.getName());
		test.addScreenCaptureFromPath(imgPath);
	}catch(IOException e1) {
		e1.printStackTrace();
	}
}


	public void onTestSkipped(ITestResult result) {

	test=extent.createTest(result.getTestClass().getName());//name of the test method
	test.assignCategory(result.getMethod().getGroups());  
	test.log(Status.SKIP,result.getName()+" Test case SKIPPED is");
	}

	public void onFinish (ITestContext context) {//it will update everything [mandatory]

	extent.flush();

	}

}
