package com.sourabhsurve.SBT_ExcelImportExportDynamically.controller;

import com.sourabhsurve.SBT_ExcelImportExportDynamically.entity.Product;
import com.sourabhsurve.SBT_ExcelImportExportDynamically.helper.importHelper;
import com.sourabhsurve.SBT_ExcelImportExportDynamically.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/excel")

public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/import")
    public ResponseEntity<?>importExcel(@RequestParam("file") MultipartFile file){
        if(importHelper.checkExcelFormat(file)){
            this.productService.save(file);
            return ResponseEntity.ok(Map.of("Message",
                    "File is Imported and data is saved to Database.."));

        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Import Excel file Only...");
    }

    @GetMapping("/get")
    public List<Product> getAllProduct(){
      return   this.productService.getAllProducts();
    }


    @GetMapping("/export")
    public ResponseEntity<Resource>exportExcel() throws IOException {
        String fileName="product.xlsx";

        ByteArrayInputStream actualData = productService.getActualData();

        InputStreamResource file=new InputStreamResource(actualData);

       ResponseEntity<Resource> body= ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                "Attachment; fileName" + fileName)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);

        return body;
    }

}
