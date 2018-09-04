package com.example.shopping.service;

import com.example.shopping.dao.UserDao;
import com.example.shopping.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// 参考
// https://blog.csdn.net/u012702547/article/details/54319508
// https://www.imooc.com/article/28116

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }
}
