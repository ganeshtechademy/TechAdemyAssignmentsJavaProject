package com.assigment.ta.qa.base;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
public class ReadEmployeeData {

        public static void main(String[] args) {
            String userWorkingDirectory = System.getProperty("user.dir");
            String filePath = userWorkingDirectory+"/EmployeeData.xlsx"; // Ensure this matches your file name
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

            } catch (Exception e) {
                System.err.println("Error reading the Excel file: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

