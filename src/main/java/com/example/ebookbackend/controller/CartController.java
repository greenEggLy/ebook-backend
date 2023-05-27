package com.example.ebookbackend.controller;

import com.example.ebookbackend.entity.CartItem;
import com.example.ebookbackend.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartItemService cartService;

    @RequestMapping(value = "/cart/get/{user_id}", method = RequestMethod.GET)
    @CrossOrigin(value = "http://localhost:3000")
    public List<CartItem> findCartByUser(@PathVariable(value = "user_id") Long user_id) {
        return cartService.findUserAll(user_id);
    }

    @RequestMapping(value = "/cart/get-item/{id}")
    @CrossOrigin(value = "http://localhost:3000")
    public CartItem findCart(@PathVariable(value = "id") Long id) {
        return cartService.findOne(id);
    }

    @RequestMapping(value = "/cart/get-items")
    @CrossOrigin(value = "http://localhost:3000")
    public List<CartItem> findCartItems(@RequestParam List<Long> id) {
        try {
            return cartService.findCartItems(id);
        } catch (Exception ignored) {
        }
        return null;
    }

    @RequestMapping(value = "/cart/add")
    @CrossOrigin(value = "http://localhost:3000")
    public void addCartItem(Long user_id, Long book_id, Long num) {
        cartService.addCartItem(user_id, book_id, num);
    }

    @RequestMapping(value = "/cart/add-num")
    @CrossOrigin(value = "http://localhost:3000")
    public void addCartItemNum(Long item_id, Long num) {
        cartService.addCartItemNum(item_id, num);
    }

    @RequestMapping(value = "/cart/minus-num")
    @CrossOrigin(value = "http://localhost:3000")
    public void minusCartItemNum(Long item_id, Long num) {
        cartService.minusCartItemNum(item_id, num);
    }

    @RequestMapping(value = "/cart/delete")
    @CrossOrigin(value = "http://localhost:3000")
    public void deleteCartItem(Long item_id) {
        cartService.deleteCartItem(item_id);
    }
}
