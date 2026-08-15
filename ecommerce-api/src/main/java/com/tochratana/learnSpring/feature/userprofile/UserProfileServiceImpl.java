package com.tochratana.learnSpring.feature.userprofile;

import com.tochratana.learnSpring.feature.userprofile.dto.PatchUserProfileRequest;
import com.tochratana.learnSpring.feature.userprofile.dto.UserProfileResponse;
import com.tochratana.learnSpring.security.AuthUtils;
import com.tochratana.learnSpring.security.KeycloakProperties;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;
    private final Keycloak keycloak;
    private final KeycloakProperties keycloakProperties;

    @Override
    @Transactional
    public UserProfileResponse patchUserProfile(PatchUserProfileRequest request) {
        String userId = AuthUtils.extractUserId();
        UserProfile userProfile = findUserProfile(userId);

        UserResource userResource = getKeycloakUser(userId);
        UserRepresentation userRepresentation = userResource.toRepresentation();

        userProfileMapper.updateEntity(userProfile, request);
        userProfileMapper.updateUserRepresentation(userRepresentation, request);

        userResource.update(userRepresentation);
        userProfileRepository.save(userProfile);

        return userProfileMapper.toResponse(userRepresentation, userProfile);
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfileResponse getUserProfile() {
        String userId = AuthUtils.extractUserId();
        UserRepresentation userRepresentation = getKeycloakUser(userId).toRepresentation();
        UserProfile userProfile = findUserProfile(userId);

        return userProfileMapper.toResponse(userRepresentation, userProfile);
    }

    private UserProfile findUserProfile(String userId) {
        return userProfileRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User profile has not been found"
                ));
    }

    private UserResource getKeycloakUser(String userId) {
        return keycloak.realm(keycloakProperties.getRealm())
                .users()
                .get(userId);
    }
}
