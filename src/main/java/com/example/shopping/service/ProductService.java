package com.example.shopping.service;

import com.alibaba.fastjson.JSONObject;
import com.example.shopping.dao.CategoryDao;
import com.example.shopping.dao.ProductDao;
import com.example.shopping.entity.Product;
import com.example.shopping.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class ProductService {

    @Autowired
    ProductDao productDao;

    @Autowired
    CategoryDao categoryDao;


    /**
     * 获取所有商品的列表
     */
    public List<Product> getAllProducts(){
        return productDao.findAll();
    }

    /**
     * 根据商品Id获取商品
     */
    public Product getProductInfo(Integer id){
        Product product = productDao.findByProductId(id);
//        JSONObject result = new JSONObject();
//        result.put("商品名称",product.getProductName());
//        result.put("商品价格",product.getPrice());
//        result.put("商品描述",product.getDesc());
//        result.put("商品类别",product.getProductCategory().getCategoryName());
//        result.put("商品库存",product.getProductStock());
        return product;
    }

    /**
     *新增商品
     */
    public Integer addProduct(Product product) {
//        JSONObject result = new JSONObject();
        Product product1 = productDao.findByProductName(product.getProductName());
        ProductCategory productCategory = categoryDao.findByCategoryName(product.getCategoryId().getCategoryName());
        if (product1 == null) {
            product1 = new Product();
            product1.setProductName(product.getProductName());
            product1.setPrice(product.getPrice());
            product1.setProductStock(product.getProductStock());
            product1.setImageUrl(product.getImageUrl());
            product1.setDesc(product.getDesc());
            product1.setCategoryId(productCategory);
            product1.setCreateTime(new Date());
            productDao.save(product1);
//            result.put("res","添加商品成功！");
            return  1;
        } else {
//            result.put("res","添加商品失败，此商品已存在!");
            return 0;
        }
    }
    /**
     * 删除商品
     */
    public void deleteProduct(Integer productId){
        productDao.deleteByProductId(productId);
    }

    /**
     * 搜索商品
     */
    public List searchProduct(String keyWords) {
        List<Product> products = productDao.findByProductNameLike(keyWords);
        if (products.size() == 0) {
            ProductCategory productCategory = categoryDao.findByCategoryNameLike(keyWords);
            List<Product> productList = productDao.findByCategoryId(productCategory);
            return productList;
        } else {
            return products;
        }
    }
}
