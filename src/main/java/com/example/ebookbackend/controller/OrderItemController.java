package com.example.ebookbackend.controller;

import com.example.ebookbackend.entity.Order;
import com.example.ebookbackend.entity.OrderItem;
import com.example.ebookbackend.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @RequestMapping(value = "/getOrderItem")
    @CrossOrigin
    public OrderItem findOrderItemById(Long order_id) {
        return orderItemService.findOne(order_id);
    }

    @RequestMapping(value = "/getUserOrderItem")
    @CrossOrigin
    public List<OrderItem> findOrderItemsByUser(Long user_id) {
        return orderItemService.findByUser(user_id);
    }

    @RequestMapping(value = "/getOrderOrderItem")
    @CrossOrigin
    public List<OrderItem> findOrderItemsByOrder(Long order_id) {
        return orderItemService.findByOrder(order_id);
    }

    @RequestMapping(value = "/getAllOrderItems")
    @CrossOrigin
    public List<OrderItem> findAllOrderItems() {
        return orderItemService.getAll();
    }
}
