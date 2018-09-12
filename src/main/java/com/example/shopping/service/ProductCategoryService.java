package com.example.shopping.service;

import com.alibaba.fastjson.JSONObject;
import com.example.shopping.dao.CategoryDao;
import com.example.shopping.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    /**
     * 添加分类
     */
    public JSONObject addCatgory(String catName){
        JSONObject result = new JSONObject();
        ProductCategory category = categoryDao.findByCategoryName(catName);
        if(category == null){
            category = new ProductCategory();
            category.setCategoryName(catName);
            category.setCreateTime(new Date());
            categoryDao.save(category);
            result.put("res","添加分类成功！");
        }else {
            result.put("res","添加分类失败，此分类已存在！");
        }
        return result;
    }

    /**
     * 删除分类
     */
    public void deleteCat(Integer catId){
        categoryDao.deleteById(catId);
    }
}
