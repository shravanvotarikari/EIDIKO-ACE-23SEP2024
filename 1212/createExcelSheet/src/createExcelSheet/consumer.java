package createExcelSheet;

import java.io.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class consumer {

    public static void main(String[] args) {
        // Sample data for demonstration
        String jsonData = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}";
        String xmlData = "<person><name>John</name><age>30</age><city>New York</city></person>";
        String delimitedData = "name,age,city\nJohn,30,New York";

        // Call the method to process and write the data to Excel
        processData(jsonData, xmlData, delimitedData);
    }

    public static void processData(String jsonData, String xmlData, String delimitedData) {
        try {
            // Create a new Excel workbook and sheet
            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("DataSheet");

            // Create header row with styling
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Data Format", "Data"};

            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.index);
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.BLUE.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            for (int i = 0; i < headers.length; i++) {
                Cell headerCell = headerRow.createCell(i);
                headerCell.setCellValue(headers[i]);
                headerCell.setCellStyle(headerStyle);
            }

            // Add data rows
            String[][] data = {
                {"JSON", jsonData},
                {"XML", xmlData},
                {"Delimited", delimitedData}
            };

            for (int i = 0; i < data.length; i++) {
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(data[i][0]);
                row.createCell(1).setCellValue(data[i][1]);
            }

            // Auto-size columns for better readability
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Save the Excel file
            String filePath = "C:\\ProgramData\\IBM\\MQSI\\Output.xls";
            File file = new File(filePath);

            // Ensure the directory exists
            file.getParentFile().mkdirs();

            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
            }

            System.out.println("Excel file created successfully at: " + filePath);

            // Close the workbook
            workbook.close();
        } catch (IOException e) {
            System.err.println("Error occurred while creating Excel file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
