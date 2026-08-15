package com.tochratana.learnSpring.feature.order;

import com.tochratana.learnSpring.feature.order.dto.CreateOrderRequest;
import com.tochratana.learnSpring.feature.order.dto.OrderResponse;
//import org.springframework.security.oauth2.jwt.Jwt;

public interface OrderService {
    OrderResponse createOrder(CreateOrderRequest createOrderRequest);
}
