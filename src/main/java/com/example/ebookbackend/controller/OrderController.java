package com.example.ebookbackend.controller;

import com.example.ebookbackend.constant.common.*;
import com.example.ebookbackend.entity.Order;
import com.example.ebookbackend.service.OrderService;
import com.example.ebookbackend.utils.Msg;
import com.example.ebookbackend.utils.MsgUtil;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000")
public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;
    @Autowired
    private KafkaTemplate<String, AddOrderMul> kafkaTemplateMul;
    @Autowired
    private KafkaTemplate<String, AddOrderOne> kafkaTemplateOne;

    @RequestMapping(value = "/order/get/{order_id}")
    Order getOrder(@PathVariable("order_id") Long order_id) {
        return orderService.findOne(order_id);
    }

    @RequestMapping(value = "/order/{user_id}")
    List<Order> getUserOrder(@PathVariable("user_id") Long user_id) {
        return orderService.findByUser(user_id);
    }

    @GetMapping(value = "/order/time/{user_id}")
    List<Order> getUserOrderByTime(@PathVariable("user_id") Long user_id, @DateTimeFormat(pattern = "yyyy-MM-dd") Date earlier, @DateTimeFormat(pattern = "yyyy-MM-dd") Date later) {
        return orderService.findTimeBetweenByUser(user_id, earlier, later);
    }

    @GetMapping(value = "/order/all")
    List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping(value = "/order/time/all")
    List<Order> getAllOrderByTime(@DateTimeFormat(pattern = "yyyy-MM-dd") Date earlier, @DateTimeFormat(pattern = "yyyy-MM-dd") Date later) {
        return orderService.findTimeBetween(earlier, later);
    }

    @GetMapping(value = "/order/sales")
    List<BookSalesForm> sortedSales(@DateTimeFormat(pattern = "yyyy-MM-dd") Date earlier, @DateTimeFormat(pattern = "yyyy-MM-dd") Date later) {
        List<Order> orders = orderService.findTimeBetween(earlier, later);
        return orderService.sortOrdersBySales(orders);
    }

    @GetMapping(value = "/order/money")
    List<BookSalesMoneyForm> sortedMoney(@DateTimeFormat(pattern = "yyyy-MM-dd") Date earlier, @DateTimeFormat(pattern = "yyyy-MM-dd") Date later) {
        List<Order> orders = orderService.findTimeBetween(earlier, later);
        return orderService.sortOrdersByMoney(orders);
    }

    @GetMapping(value = "/order/money/user")
    List<UserMoneyForm> sortUserMoney(@DateTimeFormat(pattern = "yyyy-MM-dd") Date earlier, @DateTimeFormat(pattern = "yyyy-MM-dd") Date later) {
        List<Order> orders = orderService.findTimeBetween(earlier, later);
        return orderService.sortUserByMoney(orders);
    }

    @RequestMapping(value = "/order/sales/one/{user_id}")
    List<BookSalesForm> sortedSalesByUser(@PathVariable("user_id") Long user_id, @DateTimeFormat(pattern = "yyyy-MM-dd") Date earlier, @DateTimeFormat(pattern = "yyyy-MM-dd") Date later) {
        List<Order> orders = orderService.findTimeBetweenByUser(user_id, earlier, later);
        return orderService.sortOrdersBySales(orders);
    }

    @RequestMapping(value = "/order/money/one/{user_id}")
    List<BookSalesMoneyForm> sortedMoneyByUser(@PathVariable("user_id") Long user_id, @DateTimeFormat(pattern = "yyyy-MM-dd") Date earlier, @DateTimeFormat(pattern = "yyyy-MM-dd") Date later) {
        List<Order> orders = orderService.findTimeBetweenByUser(user_id, earlier, later);
        return orderService.sortOrdersByMoney(orders);
    }

    @RequestMapping(value = "/order/add")
    Msg addOrder(Long user_id, @RequestParam List<Long> item_id) { // send bookIds and create orderItems and order
        AddOrderMul addOrder = AddOrderMul.builder().
                item_id(item_id)
                .user_id(user_id)
                .build();
        try {
            kafkaTemplateMul.send("mul-orders",
                    addOrder);
        } catch (Exception e) {
            logger.info("send error");
        }
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG);
    }


    @RequestMapping(value = "/order/add/directly")
    Msg addOrderDirectly(Long user_id, Long book_id, Long num) { // send bookIds and create orderItems and order
        var addOrder = AddOrderOne.builder().
                book_id(book_id)
                .user_id(user_id)
                .num(num)
                .build();
        kafkaTemplateOne.send("one-order",
                addOrder);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG);
    }


}
