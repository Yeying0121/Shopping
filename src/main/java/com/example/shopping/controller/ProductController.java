package com.example.shopping.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.shopping.entity.Product;
import com.example.shopping.entity.ProductCategory;
import com.example.shopping.service.ProductCategoryService;
import com.example.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getProductInfo(@PathVariable("id") String id, Model model){
        Product product = productService.getProductInfo(id);
        model.addAttribute("product",product);
        return "productInfo";
    }

    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable("productId") String productId){
        productService.deleteProduct(productId);
        return "redirect:/control";
    }

    @PostMapping("/save")
    public String saveProduct(Product product){
        product.setCreateTime(new Date());
        productService.addProduct(product);
        return "redirect:/control";
    }

    @GetMapping("/search")
    public String searchProduct(String productName,Model model){
        List<Product> products = productService.searchProduct(productName);
        model.addAttribute("products",products);
        return "search";
    }
}
