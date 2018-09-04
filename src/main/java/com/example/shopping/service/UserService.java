package com.example.shopping.service;

import com.example.shopping.dao.UserDao;
import com.example.shopping.entity.SysUser;
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
     * 根据用户名获取具体用户
     */
    public SysUser getUser(String userName){
        SysUser user = userDao.findByUsername(userName);
        return user;
    }

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
    public String updateUser(String userName){
        SysUser user = userDao.getOne(userName);
        return "success";
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
    public void deleteUser(String userName){
        userDao.deleteByUsername(userName);
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
