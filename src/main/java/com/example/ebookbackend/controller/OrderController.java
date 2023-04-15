package com.example.ebookbackend.controller;

import com.example.ebookbackend.constant.forms.BookSalesForm;
import com.example.ebookbackend.constant.forms.BookSalesMoneyForm;
import com.example.ebookbackend.entity.Order;
import com.example.ebookbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order/get/{order_id}")
    @CrossOrigin(value = "http://localhost:3000")
    Order getOrder(@PathVariable("order_id") Long order_id) {
        return orderService.findOne(order_id);
    }

    @RequestMapping(value = "/orders/get/{user_id}")
    @CrossOrigin(value = "http://localhost:3000")
    List<Order> getUserOrder(@PathVariable("user_id") Long user_id) {
        return orderService.findByUser(user_id);
    }

    @RequestMapping(value = "/orders/get-all")
    @CrossOrigin(value = "http://localhost:3000")
    List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @RequestMapping(value = "/order/get/sales/time/between")
    @CrossOrigin(value = "http://localhost:3000")
    List<BookSalesForm> sortedSales(@DateTimeFormat(pattern = "yyyy-MM-dd") Date earlier, @DateTimeFormat(pattern = "yyyy-MM-dd") Date later) {
        List<Order> orders = orderService.findTimeBetween(earlier, later);
        return orderService.sortOrdersBySales(orders);
    }

    @RequestMapping(value = "/order/get/money/time/between")
    @CrossOrigin(value = "http://localhost:3000")
    List<BookSalesMoneyForm> sortedMoney(@DateTimeFormat(pattern = "yyyy-MM-dd") Date earlier, @DateTimeFormat(pattern = "yyyy-MM-dd") Date later) {
        List<Order> orders = orderService.findTimeBetween(earlier, later);
        return orderService.sortOrdersByMoney(orders);
    }

    @RequestMapping(value = "/order/get/sales/time/between/{user_id}")
    @CrossOrigin(value = "http://localhost:3000")
    List<BookSalesForm> sortedSalesByUser(@PathVariable("user_id") Long user_id, @DateTimeFormat(pattern = "yyyy-MM-dd") Date earlier, @DateTimeFormat(pattern = "yyyy-MM-dd") Date later) {
        List<Order> orders = orderService.findTimeBetweenByUser(user_id, earlier, later);
        return orderService.sortOrdersBySales(orders);
    }

    @RequestMapping(value = "/order/get/money/time/between/{user_id}")
    @CrossOrigin(value = "http://localhost:3000")
    List<BookSalesMoneyForm> sortedMoneyByUser(@PathVariable("user_id") Long user_id, @DateTimeFormat(pattern = "yyyy-MM-dd") Date earlier, @DateTimeFormat(pattern = "yyyy-MM-dd") Date later) {
        List<Order> orders = orderService.findTimeBetweenByUser(user_id, earlier, later);
        return orderService.sortOrdersByMoney(orders);
    }

    @RequestMapping(value = "/order/add")
    @CrossOrigin(value = "http://localhost:3000")
    void addOrder(Long user_id, @RequestParam List<Long> item_id) { // send bookIds and create orderItems and order
        orderService.addOrder(user_id, item_id);
    }

    @RequestMapping(value = "/order/add/directly")
    @CrossOrigin(value = "http://localhost:3000")
    void addOrderDirectly(Long user_id, Long book_id, Long num) { // send bookIds and create orderItems and order
        orderService.addOrderDirectly(user_id, book_id, num);
    }

}
