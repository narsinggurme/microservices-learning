package com.ng.orderservice.service;

import com.ng.orderservice.dto.OrderRequest;
import com.ng.orderservice.model.Order;
import com.ng.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest)
    {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setSkuCode(orderRequest.skuCode());
        order.setQuantity(orderRequest.quantity());
        order.setPrice(java.math.BigDecimal.valueOf(orderRequest.price()));

        orderRepository.save(order);
    }
}
