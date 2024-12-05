package JTAS.Resources;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtils {
	private Workbook workbook;
    private Sheet sheet;

	public ExcelUtils(String excelPath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(excelPath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
    }

	public String getCellData(String sheetName, int rowNum, int colNum) {
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNum);
		Cell cell = row.getCell(colNum);

		DataFormatter formatter = new DataFormatter();
		return formatter.formatCellValue(cell); // Formats cell data into String
	}

	public Map<String, String> getRowDataByColumnNames(int rowIndex) {
		Map<String, String> data = new HashMap<>();
	    Row headerRow = sheet.getRow(0); // Assuming the first row is the header
	    Row dataRow = sheet.getRow(rowIndex);

	    for (Cell cell : headerRow) {
	        int columnIndex = cell.getColumnIndex();
	        String columnName = cell.getStringCellValue();
	        String cellValue = dataRow.getCell(columnIndex).toString();
	        data.put(columnName, cellValue);
	    }

	    return data;
    }
	
	
	public int getRowCount(String sheetName) {
		return workbook.getSheet(sheetName).getLastRowNum() + 1; // Total number of rows
	}

	public int getColumnCount(String sheetName) {
		return workbook.getSheet(sheetName).getRow(0).getLastCellNum(); // Total number of columns
	}

	public void closeWorkbook() throws IOException {
		workbook.close();
	}
}
