package com.tochratana.learnSpring.feature.product.dto;

import lombok.Builder;

@Builder
public record ProductResponse(
        String code,
        String name,
        Integer price,
        Integer qty,
        String description,
        Boolean isAvailable,
        String category
) {
}
