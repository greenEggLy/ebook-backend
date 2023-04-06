package com.example.ebookbackend.repository;

import com.example.ebookbackend.entity.Book;
import com.example.ebookbackend.entity.CartItem;
import com.example.ebookbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem getCartItemById(Long id);

    CartItem findCartItemById(Long id);

    List<CartItem> findCartItemsByAdder_Id(Long user_id);

    List<CartItem> findCartItemsByAdder(User user);

    CartItem getCartItemByAdderAndBook(User user, Book book);

    void deleteCartItemsByAdderAndBook(User user, Book book);
}
