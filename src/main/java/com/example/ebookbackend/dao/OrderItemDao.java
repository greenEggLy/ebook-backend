package com.example.ebookbackend.dao;

import com.example.ebookbackend.entity.OrderItem;

import java.util.List;
import java.util.Set;

public interface OrderItemDao {
    OrderItem findOne(Long id);

    Set<OrderItem> findByOrder(Long id);

    Set<OrderItem> findByBuyer(Long id);

    List<OrderItem> getAll();
}
