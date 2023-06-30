package com.sourabhsurve.SBT_ExcelImportExportDynamically.helper;

import com.sourabhsurve.SBT_ExcelImportExportDynamically.entity.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class importHelper {

    //check file type is excelType or not
    public static boolean checkExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();

        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }

    }

    //converts  excel to list of employee
    public static List<Product> convertExcelToListOfProduct(InputStream is) {
        List<Product> list = new ArrayList<>();

        try {

            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("product");

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellIterator = row.iterator();
                int cid = 0;
                Product p = new Product();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    switch (cid) {
                        case 0:
                            p.setProductId((long) cell.getNumericCellValue());
                            break;

                        case 1:
                            p.setName(cell.getStringCellValue());
                            break;

                        case 2:
                            p.setPrice((int) cell.getNumericCellValue());
                            break;


                        case 3:
                            p.setQuantity((int) cell.getNumericCellValue());
                            break;

                        default:
                            break;
                    }
                    cid++;
                }
                list.add(p);


            }


        } catch (Exception e) {
            e.printStackTrace();

        }

        return list;
    }


}

