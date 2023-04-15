package com.example.ebookbackend.repository;

import com.example.ebookbackend.entity.Order;
import com.example.ebookbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderById(Long id);

    List<Order> findOrdersByBuyer_Id(Long id);

    List<Order> findOrdersByTimeBetween(Date earlier, Date later);

    List<Order> findOrdersByTimeBefore(Date later);

    List<Order> findOrdersByTimeAfter(Date earlier);

    List<Order> findOrdersByBuyerAndTimeBetween(User buyer, Date earlier, Date later);

}
