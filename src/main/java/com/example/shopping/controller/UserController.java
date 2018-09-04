package com.example.shopping.controller;

import com.example.shopping.config.WebSecurityConfig;
import com.example.shopping.entity.User;
import com.example.shopping.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Resource
    UserService userService;

    /**
     * 用户注册
     */
    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    /**
     * 用户登录
     */
//    @ResponseBody
    @RequestMapping("/login")
    public String login(){
        // 到登录页面，可能可以校验Token
        // 若带有Token，则可以直接跳转如首页
        return "login";
    }

    /**
     * 登陆验证
     */
//
//    @PostMapping("/dologin")
//    public String dologin(User user, Model model){
//        Boolean loginSuccess = userService.login(user);
//        if(loginSuccess){
////            Cookie cookie = new Cookie(WebSecurityConfig.SESSION_KEY, user.toString());
////            response.addCookie(cookie);
//            model.addAttribute("currentUser",user);
//            return "register";
//        }else {
//            model.addAttribute("msg", "用户名或者密码错误");
//            return "login";
//        }
//    }


    @PostMapping("/doregister")
    public String doregister(User user){
        return "";
    }

    @GetMapping(value = "/delete/{userName}")
    public String deleteUser(@PathVariable("userName") String userName){
        userService.deleteUser(userName);
        return "redirect:/control";
    }


}
