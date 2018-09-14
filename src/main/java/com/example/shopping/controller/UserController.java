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
import javax.servlet.http.HttpServletRequest;
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
    @ResponseBody
    public String doregister(@RequestBody SysUser user) {
        String result = userService.addUser(user);
        return result;
    }

    @GetMapping("/index")
    public String index(){
        return "index";
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

    @GetMapping("/change_pw")
    public String toChangePage(){
        return "change_pw";
    }

    @PostMapping("/savePassword")
    @ResponseBody
    public String changePw(HttpServletRequest request){
        JSONObject result = new JSONObject();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        SysUser user = userDao.findByUsername(userName);
        String prePassword = request.getParameter("prePassword");
        if(!prePassword.equals(user.getPassword())){
            result.put("flag",0);
            result.put("res","你输入的原密码不正确，请重新输入！");
            return result.toJSONString();
        }else {
            String newPassword = request.getParameter("newPassword");
            String passwordConfirm = request.getParameter("passwordConfirm");
            if(!newPassword.equals(passwordConfirm)){
                result.put("flag",2);
                result.put("res","您两次输入的密码不一致，请重新输入！");
                return result.toJSONString();
            }else {
                user.setPassword(newPassword);
                userDao.save(user);
                result.put("flag",1);
                result.put("res","密码修改成功,请重新登录!");
                return result.toJSONString();
            }
        }
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
