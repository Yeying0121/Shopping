package com.example.shopping.controller;

import com.example.shopping.entity.ShoppingCar;
import com.example.shopping.entity.SysUser;
import com.example.shopping.service.ShoppingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shoppingCar")
public class ShoppingCarController {

    @Autowired
    ShoppingCarService shoppingCarService;

    @PostMapping("/save")
    public String addShoppingCar(Integer userId,String productId,Integer counts){
        shoppingCarService.addShoppingCar(userId,productId,counts);
        return "redirect:/shopping/detail/"+productId;
    }

    @GetMapping("/")
    public String getShoppingCars(SysUser user, Model model){
        Integer userId = user.getId();
        List<ShoppingCar> shoppingCars = shoppingCarService.getShoppingCars(userId);
        model.addAttribute("shoppingCars",shoppingCars);
        return "shoppingCar";
    }
}
