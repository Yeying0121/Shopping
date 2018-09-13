package com.example.shopping.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.shopping.entity.Product;
import com.example.shopping.entity.ProductCategory;
import com.example.shopping.entity.SysUser;
import com.example.shopping.service.ProductCategoryService;
import com.example.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/shopping")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductCategoryService productCategoryService;

    @GetMapping("/main")
    public String getProductList(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products",products);
        return "home";
    }

    @GetMapping("/detail/{id}")
    public String getProductInfo(@PathVariable("id") Integer id, Model model){
        Product product = productService.getProductInfo(id);
        model.addAttribute("product",product);
        return "productInfo";
    }

    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable("productId") Integer productId){
        productService.deleteProduct(productId);
        return "redirect:/control";
    }

    @PostMapping("/save")
    public String saveProduct(Product product,Model model) {
        Integer result = productService.addProduct(product);
        if (result == 1) {
            model.addAttribute("msg","添加商品成功");
        } else {
            model.addAttribute("msg","商品已存在，请重新添加商品！");
        }
        return "redirect:/control";
    }

    @GetMapping("/search")
    public String searchProduct(String keyWords,Model model){
        List<Product> products = productService.searchProduct(keyWords);
        model.addAttribute("products",products);
//        return "true";
        return "home";
    }
}
