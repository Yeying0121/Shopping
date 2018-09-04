package com.example.shopping.service;

import com.alibaba.fastjson.JSONObject;
import com.example.shopping.dao.ShoppingCarDao;
import com.example.shopping.entity.ShoppingCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class ShoppingCarService {

    @Autowired
    ShoppingCarDao shoppingCarDao;

    @Autowired
    ProductService productService;

    /**
     * 根据用户Id获取购物车列表
     */
    public List<ShoppingCar> getShoppingCar(Integer userId){
        List<ShoppingCar> shoppingCars = shoppingCarDao.findByUserId(userId);
        return shoppingCars;
    }

    /**
     * 添加商品到购物车
     */
    public void addShoppingCar(Integer userId,String productId,Integer counts){
        ShoppingCar shoppingCar = shoppingCarDao.findByUserIdAndProductId(userId,productId);
        if(shoppingCar == null){
            ShoppingCar shoppingCar1 = new ShoppingCar();
            shoppingCar1.getUser().setId(userId);
            shoppingCar1.setProductId(productId);
            shoppingCar1.setCounts(counts);
            shoppingCar1.setProductPrice(productService.getProductInfo(productId).getPrice()*counts);
            shoppingCarDao.save(shoppingCar1);
        }else {
            shoppingCar.setCounts(shoppingCar.getCounts()+counts);
            shoppingCar.setProductPrice(productService.getProductInfo(productId).getPrice()*shoppingCar.getCounts());
            shoppingCarDao.save(shoppingCar);
        }
    }

    /**
     * 根据用户Id获取购物车列表
     */
    public List<ShoppingCar> getShoppingCars(Integer userId){
        List<ShoppingCar> shoppingCars = shoppingCarDao.findByUserId(userId);
        return shoppingCars;

    }
}
