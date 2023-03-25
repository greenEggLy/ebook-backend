package com.example.ebookbackend.repository;

import com.example.ebookbackend.entity.Order;
import com.example.ebookbackend.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    OrderItem findOrderItemById(Long id);

    Set<OrderItem> findOrderItemsByOrder_Id(Long id);

    Set<OrderItem> findOrderItemsByOrder_Buyer_Id(Long id);
}
