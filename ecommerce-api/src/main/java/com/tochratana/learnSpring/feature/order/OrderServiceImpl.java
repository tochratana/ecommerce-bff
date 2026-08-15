package com.tochratana.learnSpring.feature.order;

import com.tochratana.learnSpring.feature.order.dto.CreateOrderRequest;
import com.tochratana.learnSpring.feature.order.dto.OrderResponse;
import com.tochratana.learnSpring.feature.product.Product;
import com.tochratana.learnSpring.feature.product.ProductRepository;
import com.tochratana.learnSpring.security.AuthUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;
    @Override
    public OrderResponse createOrder(CreateOrderRequest createOrderRequest) {

        List<OrderLine> orderLines = new ArrayList<>();
        final Order order = orderMapper.mapCreateOrderRequestToOrder(createOrderRequest);

        // validate product code
        boolean isValid = createOrderRequest.orderLines().stream().allMatch(
                orderLineDto -> {
                    boolean isExisting = productRepository.existsById(orderLineDto.productCode());
                    if(isExisting){
                        Product validProduct =
                                productRepository.findById(orderLineDto.productCode()).orElseThrow(
                                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product code has not been found"));
                        OrderLine orderLine = new OrderLine();
                        orderLine.setProduct(validProduct);
                        orderLine.setQty(orderLineDto.qty());
                        orderLine.setDiscount(orderLineDto.discount());
                        orderLine.setOrder(order);
                        orderLines.add(orderLine);
                    }
                    return isExisting;
                }
        );

        if(!isValid)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product item is invalid");

        // System data generation
        order.setOrderedAt(Instant.now());
        order.setIsDeleted(false);
         order.setOrderedBy(AuthUtils.extractUserId());
        order.setOrderLines(orderLines);

        Order savedOrder = orderRepository.save(order);

        return orderMapper.mapOrderToOrderResponse(savedOrder);
    }
}
