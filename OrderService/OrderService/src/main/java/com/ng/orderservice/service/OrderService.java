package com.ng.orderservice.service;

import com.ng.orderservice.client.InventoryClient;
import com.ng.orderservice.dto.OrderRequest;
import com.ng.orderservice.event.OrderPlacedEvent;
import com.ng.orderservice.model.Order;
import com.ng.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void placeOrder(OrderRequest orderRequest) {
        String inventoryResponse = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if ("IN STOCK".equalsIgnoreCase(inventoryResponse)) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());
            order.setPrice(java.math.BigDecimal.valueOf(orderRequest.price()));

            orderRepository.save(order);

            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(order.getOrderNumber(), orderRequest.userDetails().email());
            log.info("Sending OrderPlacedEvent to Kafka for order number: {}", order.getOrderNumber());
            kafkaTemplate.send("order_placed_topic", orderPlacedEvent);
            log.info("OrderPlacedEvent sent to Kafka for order number: {}", order.getOrderNumber());
        } else {
            throw new RuntimeException(orderRequest.skuCode() + " is not in stock, please try again later");
        }
    }
}
