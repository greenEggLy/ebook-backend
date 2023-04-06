package com.example.ebookbackend.daoImpl;

import com.example.ebookbackend.dao.OrderItemDao;
import com.example.ebookbackend.entity.Book;
import com.example.ebookbackend.entity.CartItem;
import com.example.ebookbackend.entity.Order;
import com.example.ebookbackend.entity.OrderItem;
import com.example.ebookbackend.repository.BookRepository;
import com.example.ebookbackend.repository.OrderItemRepository;
import com.example.ebookbackend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {
    @Autowired
    private OrderItemRepository itemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BookRepository bookRepository;


    @Override
    public OrderItem findOne(Long id) {
        return itemRepository.findOrderItemById(id);
    }

    @Override
    public List<OrderItem> findByOrder(Long order_id) {
        return itemRepository.findOrderItemsByOrder_Id(order_id);
    }

    @Override
    public List<OrderItem> findByBuyer(Long user_id) {
        return itemRepository.findOrderItemsByOrder_Buyer_Id(user_id);
    }

    @Override
    public List<OrderItem> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public void addOrderItems(List<CartItem> cartItems, Long order_id) {
        Order order = orderRepository.findOrderById(order_id);
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = OrderItem.builder()
                    .book(cartItem.getBook())
                    .number(cartItem.getNumber())
                    .order(order)
                    .build();
            itemRepository.save(orderItem);
        }
    }

    @Override
    public void addOrderItem(Long book_id, Long order_id, Long num) {
        Order order = orderRepository.findOrderById(order_id);
        Book book = bookRepository.getBookById(book_id);
        OrderItem orderItem = OrderItem.builder()
                .book(book)
                .number(num)
                .order(order)
                .build();
        itemRepository.save(orderItem);
    }


    @Override
    public void update(OrderItem orderItem) {
        itemRepository.save(orderItem);
    }


}
