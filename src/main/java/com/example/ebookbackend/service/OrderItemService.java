package com.example.ebookbackend.service;

import com.example.ebookbackend.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItem findOne(Long id);

    List<OrderItem> findByOrder(Long order_id);

    List<OrderItem> findByUser(Long user_id);

    List<OrderItem> getAll();

}
