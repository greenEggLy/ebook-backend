package com.example.ebookbackend.daoImpl;

import com.example.ebookbackend.dao.CartItemDao;
import com.example.ebookbackend.entity.Book;
import com.example.ebookbackend.entity.CartItem;
import com.example.ebookbackend.entity.User;
import com.example.ebookbackend.repository.BookRepository;
import com.example.ebookbackend.repository.CartItemRepository;
import com.example.ebookbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CartItemDaoImpl implements CartItemDao {
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public CartItem getOne(Long id) {
        return cartItemRepository.getCartItemById(id);
    }

    @Override
    public CartItem findCartItemById(Long id) {
        return cartItemRepository.findCartItemById(id);
    }

    @Override
    public List<CartItem> findCartItemsByIds(List<Long> ids) {
        List<CartItem> cartItems = new ArrayList<>();
        for (Long id : ids) {
            CartItem item = cartItemRepository.getCartItemById(id);
            if (item != null) cartItems.add(item);
        }
        return cartItems;
    }

    @Override
    public List<CartItem> findCartByAdder(User user) {
        return cartItemRepository.findCartItemsByAdder(user);
    }

    @Override
    public List<CartItem> sendCartItems(List<Long> cartItemIds) {
        List<CartItem> cartItems = new ArrayList<>();
        for (Long id : cartItemIds) {
            CartItem item = cartItemRepository.getCartItemById(id);
            if (item != null) cartItems.add(item);
        }
        return cartItems;
    }

    @Override
    public void addCartItem(Long user_id, Long book_id, Long num) {
        User user = userRepository.findUserById(user_id);
        Book book = bookRepository.findBookById(book_id);
        CartItem item = cartItemRepository.getCartItemByAdderAndBook(user, book);
        if (item != null) {
            Long cur_num = item.getNumber();
            item.setNumber(cur_num + num);
            cartItemRepository.save(item);
        } else {
            CartItem new_item = new CartItem();
            new_item.setAdder(user);
            new_item.setBook(book);
            new_item.setNumber(num);
            cartItemRepository.save(new_item);
        }
    }

    @Override
    public void addCartItemNum(Long item_id, Long num) {
        CartItem item = cartItemRepository.findCartItemById(item_id);
        if (item != null) {
            Long cur_num = item.getNumber();
            item.setNumber(cur_num + num);
            cartItemRepository.save(item);
        }
    }


    @Override
    public void minusCartItemNum(Long item_id, Long num) {
        CartItem item = cartItemRepository.findCartItemById(item_id);
        if (item != null) {
            Long cur_num = item.getNumber();
            item.setNumber(cur_num - num > 0 ? cur_num - num : 0);
            cartItemRepository.save(item);
        }
    }

    @Override
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }


    @Override
    public void deleteCartItems(List<Long> item_ids) {
        for (Long itemId : item_ids) {
            CartItem item = cartItemRepository.getCartItemById(itemId);
            if (item != null) cartItemRepository.delete(item);
        }
    }

}
