package com.tochratana.learnSpring.feature.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RequestProduct(

        @NotBlank(message = "Name is required")
        @Size(min = 1, max = 100)
        String name,

        String description,

        @Positive
        @NotNull
        Integer price,

        @Positive
        @NotNull
        Integer qty,

        @Positive
        @NotNull
       Integer categoryID

) {
}
