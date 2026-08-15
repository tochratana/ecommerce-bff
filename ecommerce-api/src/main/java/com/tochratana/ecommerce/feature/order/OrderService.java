package com.tochratana.ecommerce.feature.order;

import com.tochratana.ecommerce.feature.order.dto.CreateOrderRequest;
import com.tochratana.ecommerce.feature.order.dto.OrderResponse;
//import org.springframework.security.oauth2.jwt.Jwt;

public interface OrderService {
    OrderResponse createOrder(CreateOrderRequest createOrderRequest);
}
