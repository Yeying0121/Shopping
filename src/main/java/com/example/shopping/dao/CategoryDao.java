package com.example.shopping.dao;

import com.example.shopping.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao extends JpaRepository<ProductCategory,Integer> {
    @Override
    List<ProductCategory> findAll();
}
