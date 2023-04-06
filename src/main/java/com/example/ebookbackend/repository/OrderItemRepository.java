package com.example.ebookbackend.repository;

import com.example.ebookbackend.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    OrderItem findOrderItemById(Long id);

    List<OrderItem> findOrderItemsByOrder_Id(Long id);

    List<OrderItem> findOrderItemsByOrder_Buyer_Id(Long id);
}
