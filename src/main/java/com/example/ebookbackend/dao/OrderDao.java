package com.example.ebookbackend.dao;

import com.example.ebookbackend.entity.Order;

import java.util.Date;
import java.util.List;

public interface OrderDao {
    Order findOne(Long id);

    List<Order> findAll();

    List<Order> findByBuyer(Long id);

    List<Order> findTimeBetween(Date earlier, Date later);

    List<Order> findTimeBefore(Date later);

    List<Order> findTimeAfter(Date earlier);

    List<Order> findTimeBetweenByUser(Long user_id, Date earlier, Date later);

    Long addOrder(Long user_id) throws Exception;


    void deleteOrder(Long id);
}
