package com.example.ebookbackend.dao;

import com.example.ebookbackend.entity.CartItem;
import com.example.ebookbackend.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {
    OrderItem findOne(Long id);

    List<OrderItem> findByOrder(Long order_id);

    List<OrderItem> findByBuyer(Long user_id);

    List<OrderItem> getAll();


    void addOrderItems(List<CartItem> cartItems, Long order_id);

    void addOrderItem(Long book_id, Long order_id, Long num);

    void update(OrderItem orderItem);
}
