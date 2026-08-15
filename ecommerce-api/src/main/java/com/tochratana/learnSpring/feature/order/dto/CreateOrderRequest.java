package com.tochratana.learnSpring.feature.order.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrderRequest(
        String remark,

        @NotEmpty(message = "order must hae at least one item")
        List<@NotNull(message = "item can't be null") OrderLineDto> orderLines
) {
}
