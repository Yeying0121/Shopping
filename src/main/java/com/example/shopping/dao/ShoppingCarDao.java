package com.example.shopping.dao;

import com.example.shopping.entity.ShoppingCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCarDao extends JpaRepository<ShoppingCar,Integer> {

    List<ShoppingCar> findByUserId(Integer userId);

    ShoppingCar findByUserIdAndProductId(Integer userId,String productId);
}
