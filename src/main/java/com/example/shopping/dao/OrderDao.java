package com.example.shopping.dao;

import com.example.shopping.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order,Integer> {

    List<Order> findByUserId(Integer userId);

//    @Query("from Order where orderStatus like %:orderStatus% and userId")
    List<Order> findByOrderStatusLikeAndUserId(String orderStatus,Integer userId);
}
