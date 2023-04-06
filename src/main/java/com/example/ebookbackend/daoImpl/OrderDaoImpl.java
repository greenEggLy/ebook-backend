package com.example.ebookbackend.daoImpl;

import com.example.ebookbackend.dao.OrderDao;
import com.example.ebookbackend.entity.Order;
import com.example.ebookbackend.entity.User;
import com.example.ebookbackend.repository.OrderRepository;
import com.example.ebookbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Order findOne(Long id) {
        return orderRepository.findOrderById(id);
    }

    @Override
    public List<Order> findByBuyer(Long id) {
        return orderRepository.findOrdersByBuyer_Id(id);
    }

    @Override
    public List<Order> findTimeBetween(Date earlier, Date later) {
        return orderRepository.findOrdersByTimeBetween(earlier, later);
    }

    @Override
    public List<Order> findTimeBefore(Date later) {
        return orderRepository.findOrdersByTimeBefore(later);
    }

    @Override
    public List<Order> findTimeAfter(Date earlier) {
        return orderRepository.findOrdersByTimeAfter(earlier);
    }


    @Override
    public Long addOrder(Long user_id) {
        User user = userRepository.getUserById(user_id);
        Order order = Order.builder()
                .buyer(user)
                .time(new Date())
                .build();
        orderRepository.save(order);
        return order.getId();
    }
}
