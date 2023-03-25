package com.example.ebookbackend.daoImpl;

import com.example.ebookbackend.dao.OrderItemDao;
import com.example.ebookbackend.entity.OrderItem;
import com.example.ebookbackend.repository.OrderItemRepository;
import org.aspectj.weaver.ast.Or;

import java.util.List;
import java.util.Set;

public class OrderItemDaoImpl implements OrderItemDao {
    private OrderItemRepository itemRepository;

    @Override
    public OrderItem findOne(Long id) {
        return itemRepository.findOrderItemById(id);
    }

    @Override
    public Set<OrderItem> findByOrder(Long id) {
        return itemRepository.findOrderItemsByOrder_Id(id);
    }

    @Override
    public Set<OrderItem> findByBuyer(Long id) {
        return itemRepository.findOrderItemsByOrder_Buyer_Id(id);
    }

    @Override
    public List<OrderItem> getAll() {
        return itemRepository.findAll();
    }

}
