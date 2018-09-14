package com.example.shopping.service;

import com.alibaba.fastjson.JSONObject;
import com.example.shopping.dao.UserDao;
import com.example.shopping.entity.SysUser;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     * 根据id获取具体用户
     */
//    public SysUser getUser(Integer id){
//        SysUser user = userDao.getOne(id);
//        return user;
//    }

    /**
     * 获取用户列表
     */
    public List<SysUser> getAllUser(){
        List<SysUser> users = userDao.findAll();
        return users;
    }

    /**
     * 修改用户信息
     */
    public JSONObject updateUser(Integer id, String userName, String email, String sex){
        SysUser user = userDao.getOne(id);
        user.setUsername(userName);
        user.setEmail(email);
        user.setSex(sex);
        userDao.save(user);
        JSONObject result = new JSONObject();
        result.put("flag",1);
        result.put("res","资料修改成功！");
        return result;
    }


    /**
     * 新增用户
     */
    public String addUser(SysUser user){
        JSONObject result = new JSONObject();
        SysUser sysUser = userDao.findByUsername(user.getUsername());
        if (sysUser != null) {
            result.put("flag",0);
            result.put("res", "注册失败，用户名已存在！");
        } else {
            sysUser = new SysUser();
            sysUser.setUsername(user.getUsername());
            sysUser.setPassword(user.getPassword());
            sysUser.setEmail(user.getEmail());
            sysUser.setSex(user.getSex());
            user.setRegisterTime(new Date());
            userDao.save(sysUser);
            result.put("flag",1);
        }
        return result.toJSONString();
    }

    /**
     *删除用户
     */
    public void deleteUser(Integer id){
        userDao.deleteById(id);
    }

    public boolean login(SysUser user) {
        SysUser validateUser = userDao.findByUsername(user.getUsername());
        if (validateUser == null) {
            return false;
        } else {
            return validateUser.getPassword().equals(user.getPassword());
        }
    }
}
