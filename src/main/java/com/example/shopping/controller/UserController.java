package com.example.shopping.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.shopping.config.WebSecurityConfig;
import com.example.shopping.dao.UserDao;
import com.example.shopping.entity.SysUser;
import com.example.shopping.service.UserService;
import com.example.shopping.utils.CommonUtilTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/users")
public class UserController {

    @Resource
    UserService userService;

    @Autowired
    UserDao userDao;

    /**
     * 用户注册
     */
    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * 用户登录
     */
//    @ResponseBody
    @RequestMapping("/login")
    public String login() {
        // 若带有Token，则可以直接跳转如首页
        return "login";
    }


    @PostMapping("/doRegister")
    public String doregister(SysUser user, Model model) {
        SysUser sysUser = userDao.findByUsername(user.getUsername());
        if (sysUser != null) {
            model.addAttribute("msg", "注册失败，用户名已存在！");
            return "register";
        } else {
            user.setRegisterTime(new Date());
            userService.addUser(user);
            return "index";
        }
    }

    @PostMapping("/identity")
    @ResponseBody
    private SysUser identity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        SysUser user = userDao.findByUsername(userName);
        return user;
    }

    @GetMapping("/amend_info")
    public String toUpdate() {
        return "amend_info";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/control";
    }

    /**
     * 修改用户资料
     */
    @ResponseBody
    @PostMapping("/save")
    public String updateUser(@RequestBody SysUser user) {
        try {
            JSONObject result = userService.updateUser(user.getId(), user.getUsername(), user.getEmail(), user.getSex());
            return result.toJSONString();
        } catch (Exception e) {
            return CommonUtilTools.returnFailResponse(e);
        }
    }
}
