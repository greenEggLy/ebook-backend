package com.example.ebookbackend.service;

import com.example.ebookbackend.constant.common.BookSalesForm;
import com.example.ebookbackend.constant.common.BookSalesMoneyForm;
import com.example.ebookbackend.constant.common.UserMoneyForm;
import com.example.ebookbackend.entity.Order;

import java.util.Date;
import java.util.List;

public interface OrderService {
    Order findOne(Long id);

    List<Order> findByUser(Long user_id);

    List<Order> findAll();

    List<Order> findTimeBetween(Date earlier, Date later);

    List<Order> findTimeBetweenByUser(Long user_id, Date earlier, Date later);

    List<BookSalesForm> sortOrdersBySales(List<Order> orders);

    List<BookSalesMoneyForm> sortOrdersByMoney(List<Order> orders);

    List<Order> findTimeBefore(Date later);

    List<Order> findTimeAfter(Date earlier);

    List<String> addOrder(Long user_id, List<Long> cartItem_ids) throws Exception;

    String addOrderDirectly(Long user_id, Long book_id, Long num) throws Exception;

    List<UserMoneyForm> sortUserByMoney(List<Order> orders);
}
