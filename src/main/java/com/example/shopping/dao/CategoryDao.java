package com.example.shopping.dao;

import com.example.shopping.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CategoryDao extends JpaRepository<ProductCategory,Integer> {
    @Override
    List<ProductCategory> findAll();

    ProductCategory findByCategoryName(String catName);

    @Query("from ProductCategory where categoryName like %:categoryName%")
    ProductCategory findByCategoryNameLike(@Param("categoryName") String categoryName);
}
