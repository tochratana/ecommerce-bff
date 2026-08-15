package com.tochratana.ecommerce.exception;

import lombok.Builder;

@Builder
public record FieldResponse(
        String field,
        String message
) {
}
