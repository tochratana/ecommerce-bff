package com.tochratana.learnSpring.feature.userprofile;

import com.tochratana.learnSpring.feature.userprofile.dto.PatchUserProfileRequest;
import com.tochratana.learnSpring.feature.userprofile.dto.UserProfileResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-profiles")
@RequiredArgsConstructor
@SecurityRequirement(name = "keycloak")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/me")
    public UserProfileResponse getUserProfile() {
        return userProfileService.getUserProfile();
    }

    @PatchMapping("/me")
    public UserProfileResponse patchUserProfile(
            @Valid @RequestBody PatchUserProfileRequest request
    ) {
        return userProfileService.patchUserProfile(request);
    }
}
