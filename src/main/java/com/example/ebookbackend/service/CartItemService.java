package com.example.ebookbackend.service;

import com.example.ebookbackend.entity.CartItem;

import java.util.List;

public interface CartItemService {
    CartItem findOne(Long id);

    List<CartItem> findCartItems(List<Long> cartItem_ids) throws Exception;

    List<CartItem> findUserAll(Long user_id); // id is user id

    void addCartItem(Long user_id, Long book_id, Long num);

    void addCartItemNum(Long item_id, Long num);

    void minusCartItemNum(Long item_id, Long num);

    void deleteCartItem(Long id);
}
