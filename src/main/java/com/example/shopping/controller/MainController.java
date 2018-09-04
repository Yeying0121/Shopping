package com.example.shopping.controller;

import com.example.shopping.entity.Product;
import com.example.shopping.entity.ProductCategory;
import com.example.shopping.entity.SysUser;
import com.example.shopping.service.ProductCategoryService;
import com.example.shopping.service.ProductService;
import com.example.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Author: HuangHaoce
 * Create at: 2018/8/20
 **/

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductCategoryService productCategoryService;

    @RequestMapping("/")
    public String redirectToLogin () {
        return "redirect:/shopping/main";
    }

    /**
     * 管理员控制管理后台
     */
    @GetMapping("/control")
    public String control(Model model){
        List<SysUser> users = userService.getAllUser();
        List<Product> products = productService.getAllProducts();
        List<ProductCategory> productCategories = productCategoryService.getAllCategory();
        model.addAttribute("users",users);
        model.addAttribute("products",products);
        model.addAttribute("categories",productCategories);
        return "control";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
