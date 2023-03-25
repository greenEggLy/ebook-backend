package com.example.ebookbackend.daoImpl;

import com.example.ebookbackend.dao.CartItemDao;
import com.example.ebookbackend.entity.CartItem;
import com.example.ebookbackend.repository.CartItemRepository;

import java.util.List;

public class CartDaoImpl implements CartItemDao {
    CartItemRepository cartItemRepository;

    @Override
    public CartItem getOne(Long id) {
        return cartItemRepository.getCartItemById(id);
    }

    @Override
    public List<CartItem> findCartByAdder(Long id) {
        return cartItemRepository.findCartItemsByAdder_Id(id);
    }
}
