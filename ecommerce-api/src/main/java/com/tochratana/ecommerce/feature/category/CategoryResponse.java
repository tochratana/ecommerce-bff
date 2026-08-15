package com.tochratana.ecommerce.feature.category;

public record CategoryResponse(
        Integer id,
        String name,
        String description,
        String icon
) {
}
