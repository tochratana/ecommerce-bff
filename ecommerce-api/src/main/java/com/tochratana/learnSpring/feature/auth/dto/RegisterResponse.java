package com.tochratana.learnSpring.feature.auth.dto;

import lombok.Builder;

@Builder
public record RegisterResponse(
        String keycloakUserId,
        String username,
        String email,
        String firstName,
        String lastName,
        String phoneNumber,
        String gender,
        String biography
) {
}
