package com.example.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: HuangHaoce
 * Create at: 2018/8/20
 **/

@Controller
public class MainContorller {

    @RequestMapping("/")
    public String redirectToLogin () {
        return "redirect:/users/login";
    }
}
