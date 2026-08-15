package com.tochratana.ecommerce.feature.userprofile.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record PatchUserProfileRequest(
        @Size(max = 255)
        String firstName,
        @Size(max = 255)
        String lastName,
        @Size(max = 255)
        String gender,
        @Size(max = 255)
        String biography,
        @Size(max = 255)
        String profilePicture,
        @Size(max = 255)
        String jobTitle,
        @DecimalMin(value = "0.0")
        BigDecimal salary,
        @Size(max = 50)
        String phoneNumber,
        @Size(max = 255)
        String githubLink,
        @Size(max = 255)
        String facebookLink
) {
}
