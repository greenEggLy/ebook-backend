package com.example.ebookbackend.serviceImpl;

import com.example.ebookbackend.constant.forms.BookSalesForm;
import com.example.ebookbackend.constant.forms.BookSalesMoneyForm;
import com.example.ebookbackend.dao.CartItemDao;
import com.example.ebookbackend.dao.OrderDao;
import com.example.ebookbackend.dao.OrderItemDao;
import com.example.ebookbackend.entity.CartItem;
import com.example.ebookbackend.entity.Order;
import com.example.ebookbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public List<Order> findTimeBetween(Date earlier, Date later) {
        return orderDao.findTimeBetween(earlier, later);
    }

    @Override
    public List<BookSalesForm> sortOrdersBySales(List<Order> orders) {
        Map<String, Long> map = new HashMap<String, Long>();
        orders.forEach((order) -> {
            order.getItems().forEach(((orderItem) -> {
                if (map.containsKey(orderItem.getBook().getTitle())) {
                    map.put(orderItem.getBook().getTitle(), map.get(orderItem.getBook().getTitle()) + orderItem.getNumber());
                } else {
                    map.put(orderItem.getBook().getTitle(), orderItem.getNumber());
                }
            }));
        });
        List<BookSalesForm> list = new ArrayList<BookSalesForm>();
        map.forEach((key, value) -> {
            list.add(new BookSalesForm(key, value));
        });
        list.sort((o1, o2) -> {
            return o2.getSales().compareTo(o1.getSales());
        });
        return list;
    }

    @Override
    public List<BookSalesMoneyForm> sortOrdersByMoney(List<Order> orders) {
        Map<String, Float> map = new HashMap<String, Float>();
        orders.forEach((order) -> {
            order.getItems().forEach(((orderItem) -> {
                if (map.containsKey(orderItem.getBook().getTitle())) {
                    map.put(orderItem.getBook().getTitle(), map.get(orderItem.getBook().getTitle()) + orderItem.getNumber());
                } else {
                    map.put(orderItem.getBook().getTitle(), orderItem.getNumber() * orderItem.getPrice());
                }
            }));
        });
        List<BookSalesMoneyForm> list = new ArrayList<BookSalesMoneyForm>();
        map.forEach((key, value) -> {
            list.add(new BookSalesMoneyForm(key, value));
        });
        list.sort((o1, o2) -> {
            return o2.getMoney().compareTo(o1.getMoney());
        });
        return list;
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
