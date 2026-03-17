package Utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
		
	public static Object[][] getExcelData(String filePath, String sheetName) throws IOException{
		
		FileInputStream fis = new FileInputStream(filePath);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheet(sheetName);
		
		int rowCount = sheet.getLastRowNum();
		int colCount = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rowCount][colCount];
		
		for(int i = 1; i <= rowCount; i++) {
			
			Row row = sheet.getRow(i);
			
			for(int j = 0; j < colCount; j++){
				
				Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				data[i - 1][j] = cell.toString();
			}
		}
		
		wb.close();
		fis.close();
		
		return data;
	}
}
