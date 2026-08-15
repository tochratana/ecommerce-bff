package com.tochratana.learnSpring.feature.auth;

import com.tochratana.learnSpring.feature.auth.dto.RegisterRequest;
import com.tochratana.learnSpring.feature.auth.dto.RegisterResponse;

public interface AuthService {
    RegisterResponse register (RegisterRequest registerRequest);
}
