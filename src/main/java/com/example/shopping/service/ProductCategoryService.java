package com.example.shopping.service;

import com.example.shopping.dao.CategoryDao;
import com.example.shopping.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {
    @Autowired
    CategoryDao categoryDao;


    /**
     * 获取分类列表
     */
    public List<ProductCategory> getAllCategory(){
        List<ProductCategory> productCategories = categoryDao.findAll();
        return productCategories;
    }
}
