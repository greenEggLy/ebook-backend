package com.example.ebookbackend.controller;

import com.example.ebookbackend.entity.CartItem;
import com.example.ebookbackend.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartItemService cartService;

    @RequestMapping(value = "/getUserCart", method = RequestMethod.GET)
    @CrossOrigin
    public List<CartItem> findCartByUser(Long user_id) {
        return cartService.findUserAll(user_id);
    }

    @RequestMapping(value = "/getCartItem")
    @CrossOrigin
    public CartItem findCart(Long id) {
        return cartService.findOne(id);
    }

    @RequestMapping(value = "/getCartItems")
    @CrossOrigin
    public List<CartItem> findCartItems(@RequestParam List<Long> cartItem_ids) {
        return cartService.findCartItems(cartItem_ids);
    }

    @RequestMapping(value = "/addCart")
    @CrossOrigin
    public void addCartItem(Long user_id, Long book_id, Long num) {
        cartService.addCartItem(user_id, book_id, num);
    }

    @RequestMapping(value = "/addCartNum")
    @CrossOrigin
    public void addCartItemNum(Long item_id, Long num) {
        cartService.addCartItemNum(item_id, num);
    }

    @RequestMapping(value = "/minusCartNum")
    @CrossOrigin
    public void minusCartItemNum(Long item_id, Long num) {
        cartService.minusCartItemNum(item_id, num);
    }

    @RequestMapping(value = "/deleteCart")
    @CrossOrigin
    public void deleteCartItem(Long item_id) {
        cartService.deleteCartItem(item_id);
    }
}
