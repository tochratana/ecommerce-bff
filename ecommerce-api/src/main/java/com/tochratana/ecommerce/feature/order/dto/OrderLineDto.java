package com.tochratana.ecommerce.feature.order.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record OrderLineDto(
        @NotBlank(message = "product is require")
        @Size(max = 255)
        String productCode,

        @Positive
        Integer qty,

        @Min(0)
        @Max(100)
        float discount
) {
}
