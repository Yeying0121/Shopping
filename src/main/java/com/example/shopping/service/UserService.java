package com.example.shopping.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.shopping.dao.UserDao;
import com.example.shopping.dao.UserDetailDao;
import com.example.shopping.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    UserDetailDao userdetailDao;

//    @Resource
//    SessionFactory sessionFactory;

    /**
     * 删除用户
     */
    public String deleteUser(Integer id){
        userDao.deleteById(id);
        return "success";
    }

    /**
     * 获取用户列表
     */
    public List<User> getAllUser(){
        List<User> users = userDao.findAllByOrderById();
        return users;
//        JSONObject result = new JSONObject();
//        JSONArray userList = new JSONArray();
//        List<User> users = userDao.findAllByOrderById();
//        for(User user:users){
//            JSONObject tmp = new JSONObject();
//            tmp.put("id",user.getId().toString());
//            tmp.put("name",user.getName());
//            tmp.put("email",user.getEmail());
//            tmp.put("sex",user.getUserDetail().getSex());
//            userList.add(tmp);
//        }
//        result.put("count",userList.size());
//        result.put("success",true);
//        return result;
    }

    /**
     * 修改用户信息
     */
    public String updateUser(Integer id){
        User user = userDao.getOne(id);
        return "success";
    }

    /**
     * 新增用户
     */
    public void addUser(User user){
        userDao.save(user);
    }

    public boolean login(String username, String password) {
        User user = userDao.findByNameAndUserDetailPassword(username, password);
        if (null == user) {
            return false;
        } else {

            return true;
        }
    }
    /**
     * 根据用户名获取用户对象
     */
    public User getUser(String nameOrEmail){
       return userDao.findByName(nameOrEmail);
    }
}
