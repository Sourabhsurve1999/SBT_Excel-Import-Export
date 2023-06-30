package com.sourabhsurve.SBT_ExcelImportExportDynamically.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "excel_tbl")
public class Product {

    @Id
    private Long productId;

    private String Name;

    private Integer Quantity;

    private Integer Price;



}
