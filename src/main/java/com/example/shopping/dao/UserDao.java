package com.example.shopping.dao;

import com.example.shopping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,String> {

    User findByUsername(String userName);

    @Modifying
    @Transactional
    void deleteByUsername(String userName);
}
