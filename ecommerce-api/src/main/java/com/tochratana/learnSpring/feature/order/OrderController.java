package com.tochratana.learnSpring.feature.order;


import com.tochratana.learnSpring.feature.order.dto.CreateOrderRequest;
import com.tochratana.learnSpring.feature.order.dto.OrderResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@SecurityRequirement(name = "keycloak")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(
            @Valid @RequestBody CreateOrderRequest createOrderRequest
    ){
        return orderService.createOrder(createOrderRequest);
    }
}
