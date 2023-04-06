package com.example.ebookbackend.service;

import com.example.ebookbackend.entity.Order;

import java.util.Date;
import java.util.List;

public interface OrderService {
    Order findOne(Long id);

    List<Order> findByUser(Long user_id);

    List<Order> findTimeBetween(Date earlier, Date later);

    List<Order> findTimeBefore(Date later);

    List<Order> findTimeAfter(Date earlier);

    void addOrder(Long user_id, List<Long> cartItem_ids);

    void addOrderDirectly(Long user_id, Long book_id, Long num);
}
