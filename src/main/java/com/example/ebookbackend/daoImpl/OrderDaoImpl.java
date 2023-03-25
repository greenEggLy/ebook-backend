package com.example.ebookbackend.daoImpl;

import com.example.ebookbackend.dao.OrderDao;
import com.example.ebookbackend.entity.Order;
import com.example.ebookbackend.repository.OrderRepository;

import java.util.Date;
import java.util.Set;

public class OrderDaoImpl implements OrderDao {

    private OrderRepository orderRepository;

    @Override
    public Order findOne(Long id) {
        return orderRepository.findOrderById(id);
    }

    @Override
    public Set<Order> findByBuyer(Long id) {
        return orderRepository.findOrdersByBuyer_Id(id);
    }

    @Override
    public Set<Order> findTimeBetween(Date earlier, Date later) {
        return orderRepository.findOrdersByTimeBetween(earlier, later);
    }

    @Override
    public Set<Order> findTimeBefore(Date later) {
        return orderRepository.findOrdersByTimeBefore(later);
    }

    @Override
    public Set<Order> findTimeAfter(Date earlier) {
        return orderRepository.findOrdersByTimeAfter(earlier);
    }
}
