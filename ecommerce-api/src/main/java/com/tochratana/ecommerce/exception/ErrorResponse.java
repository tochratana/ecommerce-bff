package com.tochratana.ecommerce.exception;

import lombok.Builder;

import java.time.Instant;

@Builder
public record ErrorResponse(
        String status,
        int code,
        String message,
        Instant timestamp,
        Object errorsDetails
) {
}