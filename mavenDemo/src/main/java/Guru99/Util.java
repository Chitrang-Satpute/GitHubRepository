package Guru99;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

public class Util {

	public static String baseUrl = "https://www.demo.guru99.com/V4/";
	public static String chrom_Path = "D:\\Selenium\\chromedriver.exe";
	public static String File_path = "D:\\Selenium\\testData.xls";
	public static String sheet_name = "Data";
	// Implicit wait time
	public static int wait_Time = 10;

	public static String EXPECT_TITLE = "Guru99 Bank Manager HomePage";
	public static String expectedAltTest = "User or Password is not valid";

	public static String User_name = "mngr402395";
	public static String Password = "UtUnedE";

	// Get and Read Excel manager login data
	public FileInputStream fi;
	public FileOutputStream fo;
	public HSSFWorkbook workbook;
	public HSSFSheet sheet;
	public HSSFRow row;
	public HSSFCell cell;

	public int getRowcount(String sheetname) throws Exception {
		fi = new FileInputStream(File_path);
		workbook = new HSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}

	public int getCellcount(String sheetname, int rownum) throws Exception {
		fi = new FileInputStream(File_path);
		workbook = new HSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}

	public String getcellData(String sheetname, int rownum, int colnum) throws Exception {
		fi = new FileInputStream(File_path);
		workbook = new HSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);

		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}
		workbook.close();
		fi.close();
		return data;
	}

}
