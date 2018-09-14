package com.example.shopping.service;

import com.alibaba.fastjson.JSONObject;
import com.example.shopping.dao.UserDao;
import com.example.shopping.entity.SysUser;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    public void addUser(SysUser user){
        userDao.save(user);
//        SysUser user1 = userDao.findByUsername(user.getUsername());
//        if(user1!=null){
//            return "注册失败，用户名已存在！";
//        }else {
//            user1 = new SysUser();
//            user.setUsername(user.getUsername());
//            user.setEmail(user.getEmail());
//            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//            user.setSex(user.getSex());
//            return "success";
//        }
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
