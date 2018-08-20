package com.example.shopping.dao;

import com.example.shopping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    User findByUsername(String username);
}
