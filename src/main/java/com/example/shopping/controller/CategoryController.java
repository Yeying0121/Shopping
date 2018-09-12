package com.example.shopping.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.shopping.entity.ProductCategory;
import com.example.shopping.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    ProductCategoryService productCategoryService;

    @ResponseBody
    @PostMapping("/save")
    public String saveCat(@RequestBody ProductCategory category){
        JSONObject result = productCategoryService.addCatgory(category.getCategoryName());
        return result.toJSONString();
    }

    @GetMapping("/delete/{id}")
    public String deleteCat(@PathVariable("id") Integer catId){
        productCategoryService.deleteCat(catId);
        return "redirect:/control";
    }
}
