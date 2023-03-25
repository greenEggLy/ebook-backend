package com.example.ebookbackend.dao;

import com.example.ebookbackend.entity.CartItem;

import java.util.List;

public interface CartItemDao {
    CartItem getOne(Long id);

    List<CartItem> findCartByAdder(Long id);
}
