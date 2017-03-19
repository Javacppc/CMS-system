package com.itcast.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
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
	/**
	 * 同時接受Excel03和07以上的版本（但是只寫一份代碼）
	 * @throws Exception
	 */
	@Test
	public void testRead03And07Excel() throws Exception{
		String fileName = "D:\\excel\\test1.xlsx";
		FileInputStream inputStream = new FileInputStream(fileName);
		if (fileName.matches("^.*\\.(?i)((xls)|(xlsx))$")) {
			boolean is03Excel = fileName.matches("^.*\\.(?i)(xls)$");
			
			//1.讀取工作簿 
			Workbook workbook = is03Excel ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
			//2. 讀取工作表
			Sheet sheet = workbook.getSheetAt(0);
			//3. 讀取行
			Row row = sheet.getRow(3);
			//4. 讀取單元格,讀取第三行和第三列
			Cell cell = row.getCell(3);
			//cell.setCellValue("Hello World!");
			System.out.println("第三行和第三列的內容為: " + cell.getStringCellValue());
			workbook.close();
			inputStream.close();
		} 
	}
	/**
	 * 
	 * POI樣式之合並單元格,水平垂直居中
	 * @throws Exception
	 */
	@Test
	public void testExcelStyle() throws Exception{
		//創建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//創建合併單元格對象；合併第三行的第三列到第五列
		CellRangeAddress cellRangeAddress = new CellRangeAddress(2, 2, 2, 4);
		//創建單元格樣式對象
		HSSFCellStyle style = workbook.createCellStyle();
		//設置水平居中
		style.setAlignment(HorizontalAlignment.CENTER);
		//垂直居中
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		//創建字體對象
		HSSFFont font = workbook.createFont();
		//設置字體加粗
		font.setBold(true);
		//設置字體大小
		font.setFontHeightInPoints((short)16);
		//樣式加載字體
		style.setFont(font);
		
		//設置背景填充模式
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		//設置單元格背景
		style.setFillBackgroundColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
		//設置填充前景色
		style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
		
		HSSFSheet sheet = workbook.createSheet("憂鬱");
		//加載合並單元格對象（運用）
		sheet.addMergedRegion(cellRangeAddress);
		HSSFRow row = sheet.createRow(2);
		HSSFCell cell = row.createCell(2);
		//設置單元格樣式對象（運用）
		cell.setCellStyle(style);
		cell.setCellValue("憂鬱的烏龜");
		FileOutputStream out = new FileOutputStream("D:\\excel\\test.xls");
		workbook.write(out);
		
		workbook.close();
		out.close();
	}
}
