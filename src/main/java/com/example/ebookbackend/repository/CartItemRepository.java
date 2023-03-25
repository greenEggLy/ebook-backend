package com.example.ebookbackend.repository;

import com.example.ebookbackend.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem getCartItemById(Long id);

    List<CartItem> findCartItemsByAdder_Id(Long id);
}
