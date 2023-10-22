package org.example.Controller;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExelController {
    public void write(String fileName) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Новый лист");
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("Blur");

        try {
            FileOutputStream fileOutput = new FileOutputStream("C:\\Users\\Иван\\Desktop\\write.xlsx");
            workbook.write(fileOutput);
            fileOutput.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
