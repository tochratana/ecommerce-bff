package com.tochratana.learnSpring.feature.order;

import com.tochratana.learnSpring.feature.order.dto.CreateOrderRequest;
import com.tochratana.learnSpring.feature.order.dto.OrderLineDto;
import com.tochratana.learnSpring.feature.order.dto.OrderResponse;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse mapOrderToOrderResponse(Order order);
    Order mapCreateOrderRequestToOrder(CreateOrderRequest createOrderRequest);

    default List<OrderLineDto> mapOrderLineToOrderLineDto(List<OrderLine> orderLines){
        return orderLines.stream()
                .map(
                orderLine -> OrderLineDto.builder()
                    .productCode(orderLine.getProduct().getCode())
                    .qty(orderLine.getQty())
                    .discount(orderLine.getDiscount())
                    .build())
                .collect(Collectors.toList());
    }

}

