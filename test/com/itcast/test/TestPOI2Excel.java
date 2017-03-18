package com.itcast.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class TestPOI2Excel {

	/**
	 * 操作Excel2003以前的版本
	 */
	@Test
	public void testWrite03Excel() throws Exception{
		//1.創建工作簿 
		HSSFWorkbook workbook = new HSSFWorkbook();
		//2. 創建工作表
		HSSFSheet sheet = workbook.createSheet("Hello World");
		//3. 創建行
		HSSFRow row = sheet.createRow(3);
		//4. 創建單元格,創建第三行和第三列
		HSSFCell cell = row.createCell(3);
		cell.setCellValue("Hello World!");
		
		
		//輸出到硬盤
		FileOutputStream out = new FileOutputStream("D:\\excel\\test.xls");
		//把EXCEL輸出到具體的地址(如何把數據從內存輸出到硬盤中)
		workbook.write(out);//該方法甚至可以輸出至瀏覽器
		workbook.close();
		out.close();
	}
	
	@Test
	public void testRead03Excel() throws Exception{
		FileInputStream inputStream = new FileInputStream("D:\\excel\\test.xls");
		//1.讀取工作簿 
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		//2. 讀取工作表
		HSSFSheet sheet = workbook.getSheetAt(0);
		//3. 讀取行
		HSSFRow row = sheet.getRow(3);
		//4. 讀取單元格,讀取第三行和第三列
		HSSFCell cell = row.getCell(3);
		//cell.setCellValue("Hello World!");
		System.out.println("第三行和第三列的內容為: " + cell.getStringCellValue());
		/*
		//輸出到硬盤
		FileOutputStream out = new FileOutputStream("D:\\excel\\test.xls");
		//把EXCEL輸出到具體的地址(如何把數據從內存輸出到硬盤中)
		workbook.write(out);//該方法甚至可以輸出至瀏覽器
	*/	workbook.close();
		inputStream.close();
	}
	/**
	 * 操作excel07及以後的版本
	 * @throws Exception
	 */
	@Test
	public void testWrite07Excel() throws Exception{
		//1.創建工作簿 
		XSSFWorkbook workbook = new XSSFWorkbook();
		//2. 創建工作表
		XSSFSheet sheet = workbook.createSheet("筆骯龜");
		//3. 創建行
		XSSFRow row = sheet.createRow(3);
		//4. 創建單元格,創建第三行和第三列
		XSSFCell cell = row.createCell(3);
		cell.setCellValue("Hello World!");
		
		
		//輸出到硬盤
		FileOutputStream out = new FileOutputStream("D:\\excel\\test1.xlsx");
		//把EXCEL輸出到具體的地址(如何把數據從內存輸出到硬盤中)
		workbook.write(out);//該方法甚至可以輸出至瀏覽器
		workbook.close();
		out.close();
	}
}
