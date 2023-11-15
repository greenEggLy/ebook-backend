package com.example.ebookbackend.service.serviceImpl;

import com.example.ebookbackend.constant.common.BookSalesForm;
import com.example.ebookbackend.constant.common.BookSalesMoneyForm;
import com.example.ebookbackend.constant.common.UserMoneyForm;
import com.example.ebookbackend.dao.*;
import com.example.ebookbackend.entity.CartItem;
import com.example.ebookbackend.entity.Order;
import com.example.ebookbackend.service.OrderService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private CartItemDao cartItemDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private UserDao userDao;

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
    public List<Order> findTimeBetweenByUser(Long user_id, Date earlier, Date later) {
        return orderDao.findTimeBetweenByUser(user_id, earlier, later);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
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
    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
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
    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
    public List<Order> findTimeBefore(Date later) {
        return orderDao.findTimeBefore(later);
    }

    @Override
    public List<Order> findTimeAfter(Date earlier) {
        return orderDao.findTimeAfter(earlier);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public List<String> addOrder(Long user_id, List<Long> cartItem_ids) throws Exception {
        List<CartItem> cartItems = cartItemDao.findCartItemsByIds(cartItem_ids);
        Long orderId = orderDao.addOrder(user_id);
        try {
            orderItemDao.addOrderItems(cartItems, orderId);
            cartItemDao.deleteCartItems(cartItem_ids);

            // return a list of book titles
            List<String> titles = new ArrayList<String>();
            cartItems.forEach(cartItem -> {
                titles.add(cartItem.getBook().getTitle());
            });
            return titles;
        } catch (Exception e) {
            orderDao.deleteOrder(orderId);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public String addOrderDirectly(Long user_id, Long book_id, Long num) throws Exception {
        Long order_id = orderDao.addOrder(user_id);
        try {
            orderItemDao.addOrderItem(book_id, order_id, num);
            return bookDao.findOne(book_id).getTitle();
        } catch (Exception e) {
            orderDao.deleteOrder(order_id);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
    public List<UserMoneyForm> sortUserByMoney(List<Order> orders) {
        Map<Long, Double> map = new HashMap<Long, Double>();
        orders.forEach(order -> {
            order.getItems().forEach(orderItem -> {
                if (map.containsKey(order.getBuyer().getId())) {
                    map.put(order.getBuyer().getId(), map.get(order.getBuyer().getId()) + orderItem.getPrice() * orderItem.getNumber());
                } else {
                    map.put(order.getBuyer().getId(), (double) (orderItem.getPrice() * orderItem.getNumber()));
                }
            });
        });
        List<UserMoneyForm> list = new ArrayList<UserMoneyForm>();
        map.forEach((key, value) -> {
            var username = userDao.findUserById(key).getName();
            list.add(UserMoneyForm.builder().id(key).username(username).money(value).build());
        });
        list.sort((o1, o2) -> {
            return o2.getMoney().compareTo(o1.getMoney());
        });
        return list;
    }
}
