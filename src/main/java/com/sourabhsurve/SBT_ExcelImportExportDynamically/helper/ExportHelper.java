package com.sourabhsurve.SBT_ExcelImportExportDynamically.helper;

import com.sourabhsurve.SBT_ExcelImportExportDynamically.entity.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportHelper {

    public static String[] HEADERS = {
            "productId",
            "Name",
            "Quantity",
            "Price"

    };
    public static String SHEET_NAME = "product.xlsx";

    //db to excel

    public static ByteArrayInputStream dataToExcel(List<Product>list) throws IOException {

        //create workbook
        Workbook workbook=new XSSFWorkbook();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();

        try {
            //create sheet
            Sheet sheet = workbook.createSheet(SHEET_NAME);
            //create  header row
            Row row = sheet.createRow(0);

            for(int i=0;i<HEADERS.length;i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(HEADERS[i]);

            }
            //value rows
            int rowIndex=1;
            for(Product p:list){
                Row row1 = sheet.createRow(rowIndex);
                rowIndex++;
                row1.createCell(0).setCellValue(p.getProductId());
                row1.createCell(1).setCellValue(p.getName());
                row1.createCell(2).setCellValue(p.getPrice());
                row1.createCell(3).setCellValue(p.getQuantity());

            }
            workbook.write(byteArrayOutputStream);
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to Export Data In Excel...");
        }finally {
            workbook.close();
            byteArrayOutputStream.close();
        }
        return null;
    }
}
