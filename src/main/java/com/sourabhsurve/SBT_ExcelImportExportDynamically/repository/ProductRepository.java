package com.sourabhsurve.SBT_ExcelImportExportDynamically.repository;

import com.sourabhsurve.SBT_ExcelImportExportDynamically.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}
