package utils;

import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

public class ExcelUtils {
    private static final String TESTDATA_SHEET_PATH = "src/test/resources/TestData.xlsx";
    
    public static HashMap<String, String> getTestData(String sheetName, String testCaseId) {
        HashMap<String, String> dataMap = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(new File(TESTDATA_SHEET_PATH));
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);
            int rows = sheet.getPhysicalNumberOfRows();

            for (int i = 1; i < rows; i++) {
                Row currentRow = sheet.getRow(i);
                Cell idCell = currentRow.getCell(0);
                if (idCell.getStringCellValue().equalsIgnoreCase(testCaseId)) {
                    for (int j = 0; j < currentRow.getLastCellNum(); j++) {
                        String key = headerRow.getCell(j).getStringCellValue();
                        String value = currentRow.getCell(j).getStringCellValue();
                        dataMap.put(key, value);
                    }
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataMap;
    }
}
