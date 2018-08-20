package com.example.shopping.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.shopping.config.WebSecurityConfig;
import com.example.shopping.entity.User;
import com.example.shopping.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 登陆验证
     */
    @PostMapping("/dologin")
    public String dologin(User user,HttpServletResponse response,Model model){
        if (userService.login(user.getName(), user.getUserDetail().getPassword())) {
            Cookie cookie = new Cookie(WebSecurityConfig.SESSION_KEY, user.toString());
            response.addCookie(cookie);
            model.addAttribute("user", user);
            System.out.println(cookie.getName());

            return "redirect:/admin";
        } else {
            model.addAttribute("msg", "用户名或者密码错误");
            System.out.println("failure");
            return "admin/login";
        }
    }

    @PostMapping(value = "/doRegister")
    public String doRegister(User user,Model model) {
        String name = user.getName();
        User user1 = userService.getUser(user.getName());
        if (user1 != null){
            model.addAttribute("msg", "注册失败，用户名已存在");
            return "register";
        }
            else {
                Date date = new Date();
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                user.getUserDetail().setRegisterTime(sf.format(date));
                userService.addUser(user);
                return "index";
            }
    }

    /**
     * 管理员控制用户后台
     */
    @RequestMapping("/control")
    public String control(Model model){
        List<User> users = userService.getAllUser();
        model.addAttribute("users",users);
        return "control";
    }

}
