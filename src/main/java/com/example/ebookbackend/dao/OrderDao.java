package com.example.ebookbackend.dao;

import com.example.ebookbackend.entity.Order;

import java.util.Date;
import java.util.Set;

public interface OrderDao {
    Order findOne(Long id);

    Set<Order> findByBuyer(Long id);

    Set<Order> findTimeBetween(Date earlier, Date later);

    Set<Order> findTimeBefore(Date later);

    Set<Order> findTimeAfter(Date earlier);
}
