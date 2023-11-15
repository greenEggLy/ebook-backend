package com.example.ebookbackend.listener;

import com.example.ebookbackend.constant.common.AddOrderMul;
import com.example.ebookbackend.constant.common.AddOrderOne;
import com.example.ebookbackend.constant.common.CliAddOrderMul;
import com.example.ebookbackend.constant.common.CliAddOrderOne;
import com.example.ebookbackend.controller.OrderController;
import com.example.ebookbackend.listener.common.KafkaMulOrders;
import com.example.ebookbackend.listener.common.KafkaOneOrder;
import com.example.ebookbackend.service.OrderService;
import com.example.ebookbackend.websocket.WebSocketServer;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class OrderListener {
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;
    @Autowired
    private KafkaTemplate<String, KafkaMulOrders> kafkaTemplateMul;
    @Autowired
    private KafkaTemplate<String, KafkaOneOrder> kafkaTemplateOne;
    @Autowired
    private WebSocketServer ws;

    @KafkaListener(topics = "mul-orders")
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void addOrderListener(AddOrderMul payload) {
        logger.info("Received payload='{}'", payload.toString());
        try {
            List<String> titles = orderService.addOrder(payload.user_id, payload.item_id);
            logger.info(payload.user_id.toString() + " buys " + payload);
            CliAddOrderMul titleClass = CliAddOrderMul.builder().goods(titles).build();
            KafkaMulOrders data = KafkaMulOrders.builder()
                    .user_id(payload.user_id)
                    .data(titleClass)
                    .build();
            kafkaTemplateMul.send("cli-mul-orders", data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @KafkaListener(topics = "cli-mul-orders")
    void cliAddOrderListener(KafkaMulOrders payload) {
        logger.info("create orders successfully");
        try {
            ws.sendMessageToUserMul(payload.user_id.toString(), payload.data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @KafkaListener(topics = "one-order")
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void addOrderDirectlyListener(AddOrderOne payload) {
        try {
            var title = orderService.addOrderDirectly(payload.user_id, payload.book_id, payload.num);
            logger.info(payload.user_id.toString() + " buys " + payload);
            var titleClass = CliAddOrderOne.builder().goods(title).build();
            var data = KafkaOneOrder.builder()
                    .user_id(payload.user_id)
                    .data(titleClass)
                    .build();
            kafkaTemplateOne.send("cli-one-order", data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @KafkaListener(topics = "cli-one-order")
    void cliAddOrderDirectlyListener(KafkaOneOrder payload) {
        logger.info("create orders successfully");
        try {
            ws.sendMessageToUserOne(payload.user_id.toString(), payload.data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
