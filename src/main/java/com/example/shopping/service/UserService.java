package com.example.shopping.service;

import com.example.shopping.dao.UserDao;
import com.example.shopping.entity.User;
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
    public User getUser(String userName){
        User user = userDao.findByUsername(userName);
        return user;
    }

    /**
     * 获取用户列表
     */
    public List<User> getAllUser(){
        List<User> users = userDao.findAll();
        return users;
    }

    /**
     * 修改用户信息
     */
    public String updateUser(String userName){
        User user = userDao.getOne(userName);
        return "success";
    }

    /**
     * 新增用户
     */
    public void addUser(User user){
        userDao.save(user);
//        User user1 = userDao.findByUsername(user.getUsername());
//        if(user1!=null){
//            return "注册失败，用户名已存在！";
//        }else {
//            user1 = new User();
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

    public boolean login(User user) {
        User validateUser = userDao.findByUsername(user.getUsername());
        if (validateUser == null) {
            return false;
        } else {
            return validateUser.getPassword().equals(user.getPassword());
        }
    }
}
