package com.freecrm.automation.dataProviders;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {
    public static int totalRow;

    public List<Map<String, String>> getData(String excelFilePath, String sheetName) throws IOException {

        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            return readSheet(sheet);
        }
    }

    private List<Map<String, String>> readSheet(Sheet sheet) {

        Row row;
        Cell cell;
        DataFormatter formatter = new DataFormatter();

        totalRow = sheet.getLastRowNum();

        List<Map<String, String>> excelRows = new ArrayList<>();

        for (int currentRow = 1; currentRow <= totalRow; currentRow++) {

            row = sheet.getRow(currentRow);
            if (row == null) continue;

            int totalColumn = row.getLastCellNum();

            LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<>();

            for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {

                cell = row.getCell(currentColumn);

                String columnHeaderName = formatter.formatCellValue(
                        sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn)
                );

                String cellValue = formatter.formatCellValue(cell);

                columnMapdata.put(columnHeaderName, cellValue);
            }

            excelRows.add(columnMapdata);
        }

        return excelRows;
    }

    public int countRow() {
        return totalRow;
    }
}