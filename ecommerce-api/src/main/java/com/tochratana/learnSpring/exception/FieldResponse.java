package com.tochratana.learnSpring.exception;

import lombok.Builder;

@Builder
public record FieldResponse(
        String field,
        String message
) {
}
