package com.example.shopping.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.shopping.dao.ProductDao;
import com.example.shopping.dao.UserDao;
import com.example.shopping.entity.Order;
import com.example.shopping.entity.Product;
import com.example.shopping.entity.SysUser;
import com.example.shopping.service.OrderService;
import com.example.shopping.utils.CommonUtilTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    UserDao userDao;

    @Autowired
    ProductDao productDao;

    @GetMapping("/shopping_record")
    public String getOrders(@RequestParam Integer userId, Model model){
        List<Order> orders = orderService.getOrderList(userId);
        List<Order> orders1 = orderService.getUnHandleOrder(userId);
        List<Order> orders2 = orderService.getTransportOrder(userId);
        List<Order> orders3 = orderService.getReciveOrder(userId);
        model.addAttribute("orders",orders);
        model.addAttribute("orders1",orders1);
        model.addAttribute("orders2",orders2);
        model.addAttribute("orders3",orders3);
        return "shopping_record";
    }

    @GetMapping("/shopping_handle")
    public String getAllOrders(Model model){
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders",orders);
        return "shopping_handle";
    }

    @PostMapping("/handle")
    @ResponseBody
    public String handle(@RequestBody Order order){
        String res = orderService.updateOrder(order);
        return res;
    }

    @GetMapping("/new")
    public String toNewOrderPage(){
        return "new_order";
    }

    @ResponseBody
    @PostMapping("/save")
    public String addOrder(@RequestBody Order order) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        SysUser user = userDao.findByUsername(userName);
        Integer userId = user.getId();
        Integer productId = order.getProductId();
        Integer counts = order.getCounts();
        String address = order.getAddress();
        String receiver = order.getReceiver();
        Integer phoneNumber = order.getPhoneNumber();
        try {
            JSONObject res = orderService.addOrder(userId, productId, counts, address, phoneNumber,receiver);
            return res.toJSONString();
        } catch (Exception e) {
            return CommonUtilTools.returnFailResponse(e);

        }
    }
}
