package com.example.shopping.controller;

import com.example.shopping.config.WebSecurityConfig;
import com.example.shopping.entity.User;
import com.example.shopping.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
    @RequestMapping("/login")
    public String login(){
        // 到登录页面，可能可以校验Token
        // 若带有Token，则可以直接跳转如首页
        return "login";
    }

    /**
     * 登陆验证
     */
    @PostMapping("/dologin")
    public String dologin(User user, HttpServletResponse response, Model model){
        Boolean loginSuccess = userService.login(user);
        if (loginSuccess) {
            // TODO: SESSION_KEY 的机制
            Cookie cookie = new Cookie(WebSecurityConfig.SESSION_KEY, user.toString());
            response.addCookie(cookie);
            model.addAttribute("user", user);
            return "index";
        } else {
            model.addAttribute("msg", "用户名或者密码错误");
            return "login";
        }
    }

    @PostMapping(value = "/doRegister")
    public String doRegister(User user,Model model) {
//        String name = user.getName();
//        User user1 = userService.getUser(user.getName());
//        if (user1 != null){
//            model.addAttribute("msg", "注册失败，用户名已存在");
//            return "register";
//        }
//            else {
//                Date date = new Date();
//                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                user.getUserDetail().setRegisterTime(sf.format(date));
//                userService.addUser(user);
//                return "index";
//            }
        return null;
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
