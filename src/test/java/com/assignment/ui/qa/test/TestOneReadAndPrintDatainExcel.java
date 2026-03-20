package com.assignment.ui.qa.test;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class TestOneReadAndPrintDatainExcel {
    final String userWorkingDirectory = System.getProperty("user.dir");
    final String filePath = userWorkingDirectory + "/EmployeeData.xlsx";

    public static void main(String[] args) {
        TestOneReadAndPrintDatainExcel readAndPrintExcelData = new TestOneReadAndPrintDatainExcel();
        // readAndPrintExcelData.dataFromFile();
        readAndPrintExcelData.readExcelFileUsingStreamsAndPrint();
    }

    private void dataFromFile() {

        // Ensure this matches your file name
        // FileInputStream ip = new FileInputStream(userWorkingDirectory + "/EmployeeData.xlsx");
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            // Iterate through each row
            for (Row row : sheet) {
                // Iterate through each cell in the current row
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t\t");
                            break;
                        case NUMERIC:
                            System.out.print((long) cell.getNumericCellValue() + "\t\t");
                            break;
                        default:
                            System.out.print(" \t\t");
                    }
                }
                System.out.println();
            }

        } catch (Exception exception) {
            System.err.println("Error reading the Excel file: " + exception.getMessage());
            exception.printStackTrace();
        }
    }

    private void readExcelFileUsingStreamsAndPrint()  {

        // Ensure this matches your file name
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {
            DataFormatter dataFormatter = new DataFormatter();
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate through each row

            // 2. Use lambda to iterate over Sheets
            workbook.forEach(sheet1 -> {
                System.out.println("Reading Sheet: " + sheet.getSheetName());

                // 3. Use lambda to iterate over Rows
                sheet.forEach(row -> {

                    // 4. Use lambda to iterate over Cells
                    row.forEach(cell -> {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");
                    });
                    System.out.println();
                });
            });

        }
        catch (FileNotFoundException  fileNotException) {
            System.err.println("Error reading the Excel file: " + fileNotException.getMessage());
           // fileNotException.printStackTrace();
        }
        catch (Exception exception) {
            System.err.println("Error reading the Excel file: " + exception.getMessage());
           // exception.printStackTrace();
        }
    }

}

