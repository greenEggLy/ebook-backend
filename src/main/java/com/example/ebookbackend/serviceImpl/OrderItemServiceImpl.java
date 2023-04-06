package com.example.ebookbackend.serviceImpl;

import com.example.ebookbackend.dao.OrderItemDao;
import com.example.ebookbackend.dao.UserDao;
import com.example.ebookbackend.entity.Order;
import com.example.ebookbackend.entity.OrderItem;
import com.example.ebookbackend.entity.User;
import com.example.ebookbackend.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemDao orderItemDao;

    @Override
    public OrderItem findOne(Long id) {
        return orderItemDao.findOne(id);
    }

    @Override
    public List<OrderItem> findByOrder(Long order_id) {
        return orderItemDao.findByOrder(order_id);
    }

    @Override
    public List<OrderItem> findByUser(Long user_id) {
        return orderItemDao.findByBuyer(user_id);
    }

    @Override
    public List<OrderItem> getAll() {
        return orderItemDao.getAll();
    }
}
