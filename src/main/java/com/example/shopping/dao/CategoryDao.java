package com.example.shopping.dao;

import com.example.shopping.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CategoryDao extends JpaRepository<ProductCategory,Integer> {
    @Override
    List<ProductCategory> findAll();

    ProductCategory findByCategoryName(String catName);

    @Modifying
    @Transactional
    void deleteByCategoryId(Integer catId);
}
