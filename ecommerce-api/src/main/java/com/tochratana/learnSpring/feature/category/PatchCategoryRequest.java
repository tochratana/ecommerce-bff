package com.tochratana.learnSpring.feature.category;

import jakarta.validation.constraints.Size;

public record PatchCategoryRequest(
        @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
        String name,

        String description,

        String icon
) {
}
