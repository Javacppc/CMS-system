package com.taxsys.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.taxsys.nsfw.user.entity.User;
/**
 * 導出Excel的工具類
 * @author zhuxiaodong
 *
 */
public class ExcelUtil {
	/**
	 * 
	 * @param listUser 將要導出的用戶列表
	 * @param outputStream 將Excel輸出到的輸出流（在本系統中是ServletOutputStream 即輸出到瀏覽器中）
	 */
	public static void exportExcel(List<User> listUser, ServletOutputStream outputStream) {
		try {
			//1.創建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			//1.1創建合並單元格
			CellRangeAddress address = new CellRangeAddress(0, 0, 0, 4);
			//1.2頭標題樣式
			HSSFCellStyle style1 = createCellStyle(workbook, (short)16);
			//1.3列標題樣式
			HSSFCellStyle style2 = createCellStyle(workbook, (short)13);
			//2.創建工作表
			HSSFSheet sheet = workbook.createSheet();
			//2.1加載合並單元格對象
			sheet.addMergedRegion(address);
			//設置工作表的默認列寬
			sheet.setDefaultColumnWidth(20);
			//3.創建行
			//3.1創建頭標題行，並且設置頭標題
			//對於合並單元格後的區域只能存儲到它最左上角的區域中
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell(0);
			cell.setCellValue("用戶列表");
			//單元格加載樣式
			cell.setCellStyle(style1);
			//3.2創建列標題行，並且設置列標題
			String[] title = new String[]{"用戶名", "帳號", "所屬部門", "性別", "電子郵箱"};
			HSSFRow row1 = sheet.createRow(1);
			for (int i = 0; i < title.length; ++i) {
				HSSFCell cell1 = row1.createCell(i);
				cell1.setCellStyle(style2);
				cell1.setCellValue(title[i]);
			}
			//操作單元格；將用戶列表寫入Excel
			if (listUser != null) {
				for (int i = 0; i < listUser.size(); ++i) {
					HSSFRow row2 = sheet.createRow(i + 2);
					HSSFCell cell2 = row2.createCell(0);
					cell2.setCellValue(listUser.get(i).getName());
					HSSFCell cell3 = row2.createCell(1);
					cell3.setCellValue(listUser.get(i).getAccount());
					HSSFCell cell4 = row2.createCell(2);
					cell4.setCellValue(listUser.get(i).getDept());
					HSSFCell cell5 = row2.createCell(3);
					cell5.setCellValue(listUser.get(i).isGender()?"男":"女");
					HSSFCell cell6 = row2.createCell(4);
					cell6.setCellValue(listUser.get(i).getEmail());
				}
			}
			//輸出
			workbook.write(outputStream);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 創建樣式的函數
	 * @param workbook 工作簿
	 * @param fontSize 設置的字體大小
	 * @return
	 */
	public static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short fontSize) {
		HSSFCellStyle style = workbook.createCellStyle();
		//設置水平和垂直居中樣式
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		//設置字體樣式（加粗，大小）
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints(fontSize);
		font.setBold(true);
		//樣式加載字體
		style.setFont(font);
		return style;
	}
}
