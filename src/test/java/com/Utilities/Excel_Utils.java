 package com.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Excel_Utils {

	    public FileInputStream fi;    //Reading data from Excel
	    public FileOutputStream fo;   //Write data in Excel
	    public XSSFWorkbook workbook; //Access WB
	    public XSSFSheet sheet;       //Access SH
	    public XSSFRow row;           //Access row
	    public XSSFCell cell;         //Access cell
	    public CellStyle style;       //Colour
	    String path;

	    //TO PROVIDE A PATH OF XL FILE FROM DATAPROVIDER CLASS
	    public Excel_Utils(String path) {
	        this.path = path;
	    }
	    

	    public int getRowCount(String sheetName) throws IOException {
	        fi = new FileInputStream(path);        //Give file path
	        workbook = new XSSFWorkbook(fi);       //workbook will get exact xlfile
	        sheet = workbook.getSheet(sheetName); //sheet will get inside sheet structure by sheet name given in dataprovider
	        int rowcount = sheet.getLastRowNum(); //using sheet structure ask for last row num to get row count
	        workbook.close();
	        fi.close();
	        return rowcount;
	    }
	    
	
	public int getCellCount(String sheetName, int rownum) throws IOException {
	    fi = new FileInputStream(path);        //Give file path
	    workbook = new XSSFWorkbook(fi);      //workbook will get exact xlfile
	    sheet = workbook.getSheet(sheetName); //sheet will get inside sheet structure by sheet name given in dataprovider
	    row = sheet.getRow(rownum);           //row = stores 1 as starting row ,if we give 0 it includes SL.no colum also
	    int cellcount = row.getLastCellNum(); //it start from 1st row and count 1st row last columns forwarded end 
	    workbook.close();
	    fi.close();
	    return cellcount;
	}
	

	public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
	    fi = new FileInputStream(path);			
	    workbook = new XSSFWorkbook(fi);
	    sheet = workbook.getSheet(sheetName); //Access sheet structure
	    row = sheet.getRow(rownum);           //Takes which row number in for(i=1,2,3..<totalrow) rownum=i 
	    cell = row.getCell(colnum);           //Takes which row number in for(i=1,2,3..<totalrow) rownum=i
	    
	    //To get the results data or collect data
	    DataFormatter formatter = new DataFormatter();
	    String data;
	    
	    try {
	        data = formatter.formatCellValue(cell); //We need only cell value right?,give cell num it will give Data
	    } catch (Exception e) {
	        data = "";
	    }
	    workbook.close();
	    fi.close();
	    return data;
	}
	
	
	public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
	    File xlfile = new File(path);
	    if (!xlfile.exists()) {
	        workbook = new XSSFWorkbook();
	        fo = new FileOutputStream(path);
	        workbook.write(fo);
	    }

	    fi = new FileInputStream(path);
	    workbook = new XSSFWorkbook(fi);

	    if (workbook.getSheetIndex(sheetName) == -1) {
	        workbook.createSheet(sheetName);
	    }
	    sheet = workbook.getSheet(sheetName);

	    if (sheet.getRow(rownum) == null) {
	        sheet.createRow(rownum);
	    }

	    row = sheet.getRow(rownum);
	    cell = row.createCell(colnum);
	    cell.setCellValue(data);
	    fo = new FileOutputStream(path);
	    workbook.write(fo);
	    workbook.close();
	    fo.close();
	    fi.close();
	}
	
	

}
