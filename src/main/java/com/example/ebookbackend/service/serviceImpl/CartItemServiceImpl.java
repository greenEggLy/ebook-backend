package com.example.ebookbackend.service.serviceImpl;

import com.example.ebookbackend.dao.CartItemDao;
import com.example.ebookbackend.dao.UserDao;
import com.example.ebookbackend.entity.CartItem;
import com.example.ebookbackend.entity.User;
import com.example.ebookbackend.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemDao cartItemDao;
    @Autowired
    private UserDao userDao;

    @Override
    public CartItem findOne(Long id) {
        return cartItemDao.findCartItemById(id);
    }

    @Override
    public List<CartItem> findCartItems(List<Long> cartItem_ids) throws Exception {
        return cartItemDao.findCartItemsByIds(cartItem_ids);
    }

    @Override
    public List<CartItem> findUserAll(Long user_id) {
        User user = userDao.findUserById(user_id);
        return cartItemDao.findCartByAdder(user);
    }

    @Override
    public void addCartItem(Long user_id, Long book_id, Long num) {
        cartItemDao.addCartItem(user_id, book_id, num);
    }

    @Override
    public void addCartItemNum(Long item_id, Long num) {
        cartItemDao.addCartItemNum(item_id, num);
    }


    @Override
    public void minusCartItemNum(Long item_id, Long num) {
        cartItemDao.minusCartItemNum(item_id, num);
    }

    @Override
    public void deleteCartItem(Long id) {
        cartItemDao.deleteCartItem(id);
    }
}
