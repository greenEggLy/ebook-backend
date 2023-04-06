package com.example.ebookbackend.serviceImpl;

import com.example.ebookbackend.dao.CartItemDao;
import com.example.ebookbackend.dao.OrderDao;
import com.example.ebookbackend.dao.OrderItemDao;
import com.example.ebookbackend.entity.CartItem;
import com.example.ebookbackend.entity.Order;
import com.example.ebookbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private CartItemDao cartItemDao;

    @Override
    public Order findOne(Long id) {
        return orderDao.findOne(id);
    }

    @Override
    public List<Order> findByUser(Long user_id) {
        return orderDao.findByBuyer(user_id);
    }

    @Override
    public List<Order> findTimeBetween(Date earlier, Date later) {
        return orderDao.findTimeBetween(earlier, later);
    }

    @Override
    public List<Order> findTimeBefore(Date later) {
        return orderDao.findTimeBefore(later);
    }

    @Override
    public List<Order> findTimeAfter(Date earlier) {
        return orderDao.findTimeAfter(earlier);
    }

    @Override
    public void addOrder(Long user_id, List<Long> cartItem_ids) {
        List<CartItem> cartItems = cartItemDao.findCartItemsByIds(cartItem_ids);
        Long orderId = orderDao.addOrder(user_id);
        orderItemDao.addOrderItems(cartItems, orderId);
        cartItemDao.deleteCartItems(cartItem_ids);
    }

    @Override
    public void addOrderDirectly(Long user_id, Long book_id, Long num) {
        Long order_id = orderDao.addOrder(user_id);
        orderItemDao.addOrderItem(book_id, order_id, num);
    }
}
