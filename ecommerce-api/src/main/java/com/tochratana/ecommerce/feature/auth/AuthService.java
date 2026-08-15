package com.tochratana.ecommerce.feature.auth;

import com.tochratana.ecommerce.feature.auth.dto.RegisterRequest;
import com.tochratana.ecommerce.feature.auth.dto.RegisterResponse;

public interface AuthService {
    RegisterResponse register (RegisterRequest registerRequest);
}
