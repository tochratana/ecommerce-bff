package com.tochratana.learnSpring.feature.order.dto;

import com.tochratana.learnSpring.feature.order.OrderLine;

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
