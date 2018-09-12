package com.example.shopping.dao;

import com.example.shopping.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product,Integer> {
    @Override
    List<Product> findAll();

    Product findByProductId(Integer productId);

    Product findByProductName(String productName);

    @Modifying
    @Transactional
    void deleteByProductId(Integer productId);

    @Query("from Product where productName like %:productName%")
    List<Product> findByProductNameLike(@Param("productName") String productName);
}
