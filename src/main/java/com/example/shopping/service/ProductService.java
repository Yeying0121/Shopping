package com.example.shopping.service;

import com.alibaba.fastjson.JSONObject;
import com.example.shopping.dao.ProductDao;
import com.example.shopping.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductDao productDao;

    /**
     * 获取所有商品的列表
     */
    public List<Product> getAllProducts(){
        return productDao.findAll();
    }

    /**
     * 根据商品Id获取商品
     */
    public Product getProductInfo(String id){
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
    public void addProduct(Product product){
        productDao.save(product);
    }

    /**
     * 删除商品
     */
    public void deleteProduct(String productId){
        productDao.deleteByProductId(productId);
    }

    /**
     * 搜索商品
     */
    public List<Product> searchProduct(String productName){
        List<Product> products = productDao.findByProductNameLike(productName);
        return products;
    }
}
