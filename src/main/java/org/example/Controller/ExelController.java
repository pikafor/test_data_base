package org.example.Controller;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ExelController {
    private void setRegionBorderWithMedium(CellRangeAddress region, Sheet sheet) {
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, region, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, region, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, region, sheet);
        RegionUtil.setBorderTop(BorderStyle.MEDIUM, region, sheet);
    }
    public void write(String fileName, ResultSetMetaData resultSetMetaData, ResultSet resultSet) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Новый лист");
        XSSFRow row = sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(0, 2, 1, 2));

        try {
            row.createCell(0).setCellValue("№");
            setRegionBorderWithMedium(CellRangeAddress.valueOf("A1:A3"), sheet);
            row.createCell(1).setCellValue("ФИО");
            setRegionBorderWithMedium(CellRangeAddress.valueOf("B1:C3"), sheet);
            int _row = 4;
            for (int i = 4; i < resultSetMetaData.getColumnCount() + 1; i++) {
                row.createCell(_row - 1).setCellValue(resultSetMetaData.getColumnName(i));
                sheet.addMergedRegion(new CellRangeAddress(0, 0, _row - 1, _row));
                setRegionBorderWithMedium(new CellRangeAddress(0, 0, _row - 1, _row), sheet);
                _row += 2;
            }

            int count = 2;
            while (resultSet.next()) {
                count++;
                XSSFRow row1 = sheet.createRow(count);
                row1.createCell(0).setCellValue(resultSet.getString(1));
                setRegionBorderWithMedium(new CellRangeAddress(count, count, 0, 1), sheet);
                row1.createCell(1).setCellValue(resultSet.getString(2) + " " + resultSet.getString(3));
                sheet.addMergedRegion(new CellRangeAddress(count, count, 1, 2));
                setRegionBorderWithMedium(new CellRangeAddress(count, count, 1, 2), sheet);

//                for (int i = 2; i < resultSetMetaData.getColumnCount() + 1; i++) {
//                    row1.createCell(i - 1).setCellValue(resultSet.getString(i));
//                }
            }
            setRegionBorderWithMedium(CellRangeAddress.valueOf("A1:E6"), sheet);
            FileOutputStream fileOutput = new FileOutputStream("C:\\Users\\Иван\\Desktop\\write.xlsx");
            workbook.write(fileOutput);
            fileOutput.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
