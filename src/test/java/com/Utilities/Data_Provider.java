package com.Utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class Data_Provider {

	//DataProvider1
	@DataProvider(name="LoginData")   //we have to give name which we can use in testCase class by this  name
	public Object[][] getData() throws IOException {
		
	    String path = "C:\\Users\\HP\\Desktop\\AUTOMATION TESTING\\OpenCart_Project\\Testdata\\OpenCart_Login.xlsx"; // taking x1 file from testData
	    Excel_Utils xlutil = new Excel_Utils(path); // creating an object for XLUtility
	    
	    int totalrows = xlutil.getRowCount("Sheet1");
	    int totalcols = xlutil.getCellCount("Sheet1", 1);
	    
	    Object logindata[][] = new Object[totalrows][totalcols]; // created for two-dimensional array object to store 
	    
	    for (int i = 1; i <= totalrows; i++)
	    { // 1 // read the data from x1 storing in two-dimensional array row number
	        for (int j = 0; j < totalcols; j++) 
	        { // 0 // i is rows, j is column number
	            logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j); // 1, 0
	        }
	    }
	    return logindata; // returning two-dimensional array
	}
	
	//DataProvider2
	
	//DataProvider3
}
