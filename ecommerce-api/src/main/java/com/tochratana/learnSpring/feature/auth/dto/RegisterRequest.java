package com.tochratana.learnSpring.feature.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "Username is require")
        @Size(min = 3, max = 255)
        String username,

        @NotBlank(message = "Password is require")
        @Size(max = 255)
        String password,

        @NotBlank(message = "Password is require")
        @Size(max = 255)
        String confirmPassword,

        @Email
        @NotBlank(message = "Email is require")
        @Size(max = 255)
        String email,

        @NotBlank(message = "firstname is require")
        @Size(max = 255)
        String firstName,

        @NotBlank(message = "lastname is require")
        @Size(max = 255)
        String lastName,

        @Size(min = 9, max = 50)
        String phoneNumber,

        @Size(max = 255)
        String gender,

        @Size(max = 255)
        String biography
) {
}
