package com.tochratana.ecommerce.feature.order.dto;

import com.tochratana.ecommerce.feature.order.OrderLine;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record OrderResponse(
        UUID orderId,
        Instant orderedAt,
        String orderedBy,
        Boolean isDeleted,
        String remark,
        List<OrderLineDto> orderLines
) {
}
