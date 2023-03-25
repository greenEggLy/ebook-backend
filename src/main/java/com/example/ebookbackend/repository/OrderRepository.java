package com.example.ebookbackend.repository;

import com.example.ebookbackend.entity.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderById(Long id);

    Set<Order> findOrdersByBuyer_Id(Long id);

    Set<Order> findOrdersByTimeBetween(Date earlier, Date later);

    Set<Order> findOrdersByTimeBefore(Date later);

    Set<Order> findOrdersByTimeAfter(Date earlier);

}
