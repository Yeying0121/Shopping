package com.example.shopping.service;

import com.alibaba.fastjson.JSONObject;
import com.example.shopping.dao.ShoppingCarDao;
import com.example.shopping.dao.UserDao;
import com.example.shopping.entity.Product;
import com.example.shopping.entity.ShoppingCar;
import com.example.shopping.entity.SysUser;
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

    @Autowired
    UserDao userDao;

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
    public JSONObject addShoppingCar(Integer userId,Integer productId,Integer counts){
        JSONObject result = new JSONObject();
        ShoppingCar shoppingCar = shoppingCarDao.findByUserIdAndProductId(userId,productId);
        SysUser user = userDao.getOne(userId);
        Product product = productService.getProductInfo(productId);
        if(shoppingCar == null){
            ShoppingCar shoppingCar1 = new ShoppingCar();
            shoppingCar1.setUser(user);
            shoppingCar1.setProductId(productId);
            shoppingCar1.setCounts(counts);
            shoppingCar1.setProductName(product.getProductName());
            shoppingCar1.setProductPrice(product.getPrice()*counts);
            shoppingCarDao.save(shoppingCar1);
        }else {
            shoppingCar.setCounts(shoppingCar.getCounts()+counts);
            shoppingCar.setProductPrice(productService.getProductInfo(productId).getPrice()*shoppingCar.getCounts());
            shoppingCarDao.save(shoppingCar);
        }
        result.put("flag",1);
        result.put("res","添加购物车成功！");
        return result;
    }

    /**
     * 根据用户Id获取购物车列表
     */
    public List<ShoppingCar> getShoppingCars(Integer userId){
        List<ShoppingCar> shoppingCars = shoppingCarDao.findByUserId(userId);
        return shoppingCars;

    }

    /**
     * 删除购物车
     */
    public void deleteShoppingCar(Integer id){
        shoppingCarDao.deleteById(id);
    }
}
