package com.example.ebookbackend.dao;

import com.example.ebookbackend.entity.CartItem;
import com.example.ebookbackend.entity.User;

import java.util.List;

public interface CartItemDao {
    CartItem getOne(Long id);

    CartItem findCartItemById(Long id);

    List<CartItem> findCartItemsByIds(List<Long> ids) throws Exception;

    List<CartItem> findCartByAdder(User user);

    List<CartItem> sendCartItems(List<Long> cartItemIds);

    void addCartItem(Long user_id, Long book_id, Long num);

    void addCartItemNum(Long item_id, Long num);

    void minusCartItemNum(Long item_id, Long num);

    void deleteCartItem(Long id);

    void deleteCartItems(List<Long> item_ids);

}
