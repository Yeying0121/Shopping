package com.example.shopping.service;

import com.alibaba.fastjson.JSONObject;
import com.example.shopping.dao.OrderDao;
import com.example.shopping.dao.ProductDao;
import com.example.shopping.dao.UserDao;
import com.example.shopping.entity.Order;
import com.example.shopping.entity.Product;
import com.example.shopping.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ProductDao productDao;

    /**
     * 获取所有网站订单
     */
    public List getAllOrders(){
        List<Order> orders = orderDao.findAll();
        return orders;
    }

    /**
     * 获取某个用户所有订单列表
     */
    public List getOrderList(Integer userId){
        List<Order> orders = orderDao.findByUserId(userId);
        return orders;
    }

    /**
     * 获取待发货订单
     */
    public List getUnHandleOrder(Integer userId){
        List<Order> orders = orderDao.findByOrderStatusLikeAndUserId("待发货",userId);
        return orders;
    }

    /**
     * 获取运输中订单
     */
    public List getTransportOrder(Integer userId){
        List<Order> orders = orderDao.findByOrderStatusLikeAndUserId("配送中",userId);
        return orders;
    }
    /**
     * 获取已收货订单
     */
    public List getReciveOrder(Integer userId){
        List<Order> orders = orderDao.findByOrderStatusLikeAndUserId("已收货",userId);
        return orders;
    }

    /**
     * 新增订单
     */
    public JSONObject addOrder(Integer userId,Integer productId,Integer counts,String address,Integer phoneNumber,String recevier){
        JSONObject result = new JSONObject();
        SysUser user = userDao.getOne(userId);
        Product product = productDao.findByProductId(productId);
        Order order = new Order();
        order.setOrderStatus("待发货！");
        order.setUser(user);
        order.setCounts(counts);
        order.setProductId(productId);
        order.setAddress(address);
        order.setPhoneNumber(phoneNumber);
        order.setReceiver(recevier);
        order.setPrice(product.getPrice()*counts);
        order.setCreateTime(new Date());
        orderDao.save(order);
        result.put("res","订单创建成功！");
        return result;
    }

    /**
     * 修改订单状态
     */
    public String updateOrder(Order order){
        JSONObject result = new JSONObject();
        Order order1 = orderDao.getOne(order.getId());
        order1.setOrderStatus(order.getOrderStatus());
        orderDao.save(order1);
        result.put("res","修改订单状态成功！");
        return result.toJSONString();
    }
}
