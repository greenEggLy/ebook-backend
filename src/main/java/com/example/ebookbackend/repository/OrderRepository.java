package com.example.ebookbackend.repository;

import com.example.ebookbackend.entity.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderById(Long id);

    List<Order> findOrdersByBuyer_Id(Long id);

    List<Order> findOrdersByTimeBetween(Date earlier, Date later);

    List<Order> findOrdersByTimeBefore(Date later);

    List<Order> findOrdersByTimeAfter(Date earlier);

}
