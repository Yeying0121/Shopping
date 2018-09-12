package com.example.shopping.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.shopping.dao.UserDao;
import com.example.shopping.entity.Product;
import com.example.shopping.entity.ShoppingCar;
import com.example.shopping.entity.SysUser;
import com.example.shopping.service.ShoppingCarService;
import com.example.shopping.utils.CommonUtilTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/shoppingCar")
public class ShoppingCarController {

    @Autowired
    ShoppingCarService shoppingCarService;

    @Autowired
    UserDao userDao;

    @ResponseBody
    @PostMapping("/save")
    public String addShoppingCar(@RequestBody ShoppingCar shoppingCar) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        SysUser user = userDao.findByUsername(userName);
        Integer userId = user.getId();
        Integer productId = shoppingCar.getProductId();
        Integer counts = shoppingCar.getCounts();
        try {
            JSONObject res = shoppingCarService.addShoppingCar(userId, productId, counts);
            return res.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            return CommonUtilTools.returnFailResponse(e);
        }
    }

    @GetMapping("")
    public String getShoppingCars(@RequestParam Integer userId, Model model){
        List<ShoppingCar> shoppingCars = shoppingCarService.getShoppingCars(userId);
        model.addAttribute("shoppingCars",shoppingCars);
        return "shoppingCar";
    }
}
