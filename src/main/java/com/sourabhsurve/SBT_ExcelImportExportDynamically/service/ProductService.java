package com.sourabhsurve.SBT_ExcelImportExportDynamically.service;

import com.sourabhsurve.SBT_ExcelImportExportDynamically.entity.Product;
import com.sourabhsurve.SBT_ExcelImportExportDynamically.helper.ExportHelper;
import com.sourabhsurve.SBT_ExcelImportExportDynamically.helper.importHelper;
import com.sourabhsurve.SBT_ExcelImportExportDynamically.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void save(MultipartFile file) {

        try {
            List<Product> products = importHelper.convertExcelToListOfProduct(file.getInputStream());
            this.productRepository.saveAll(products);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Product>getAllProducts(){
       return this.productRepository.findAll();
    }

    public ByteArrayInputStream getActualData() throws IOException {
        List<Product> all = productRepository.findAll();

        ByteArrayInputStream byteArrayInputStream= ExportHelper.dataToExcel(all);
        return byteArrayInputStream;

    }
}
