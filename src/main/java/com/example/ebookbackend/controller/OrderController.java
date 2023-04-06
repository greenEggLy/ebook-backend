package com.example.ebookbackend.controller;

import com.example.ebookbackend.entity.Order;
import com.example.ebookbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/getOrder")
    @CrossOrigin
    Order getOrder(Long order_id) {
        return orderService.findOne(order_id);
    }

    @RequestMapping(value = "/getUserOrders")
    @CrossOrigin
    List<Order> getUserOrder(Long user_id) {
        return orderService.findByUser(user_id);
    }

    @RequestMapping(value = "/getTimeBetOrder")
    @CrossOrigin
    List<Order> findTimeBetOrder(Date earlier, Date later) {
        return orderService.findTimeBetween(earlier, later);
    }

    @RequestMapping(value = "/getTimeBeforeOrder")
    @CrossOrigin
    List<Order> findTimeBeforeOrder(Date later) {
        return orderService.findTimeBefore(later);
    }

    @RequestMapping(value = "/getTimeAfterOrder")
    @CrossOrigin
    List<Order> findTimeAfterOrder(Date earlier) {
        return orderService.findTimeAfter(earlier);
    }

    @RequestMapping(value = "/addOrder")
    @CrossOrigin
    void addOrder(Long user_id, @RequestParam List<Long> item_id) { // send bookIds and create orderItems and order
        orderService.addOrder(user_id, item_id);
    }

    @RequestMapping(value = "/addOrderDirectly")
    @CrossOrigin
    void addOrderDirectly(Long user_id, Long book_id, Long num) { // send bookIds and create orderItems and order
        orderService.addOrderDirectly(user_id, book_id, num);
    }

}
