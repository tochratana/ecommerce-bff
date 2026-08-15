package com.tochratana.learnSpring.feature.auth;

import com.tochratana.learnSpring.feature.auth.dto.RegisterRequest;
import com.tochratana.learnSpring.feature.auth.dto.RegisterResponse;
import com.tochratana.learnSpring.feature.userprofile.UserProfile;
import com.tochratana.learnSpring.feature.userprofile.UserProfileRepository;
import com.tochratana.learnSpring.security.KeycloakProperties;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Service
@Slf4j
public class AuthServiceImpl implements AuthService{

    private final UserProfileRepository userProfileRepository;
    private final Keycloak keycloak;
    private final KeycloakProperties keycloakProperties;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {

        // Validate the two password fields before calling Keycloak.
        if (!registerRequest.password().equals(registerRequest.confirmPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords do not match");
        }

        // Build the user that will be stored in Keycloak.
        UserRepresentation user = new UserRepresentation();
        user.setUsername(registerRequest.username());
        user.setEmail(registerRequest.email());
        user.setFirstName(registerRequest.firstName());
        user.setLastName(registerRequest.lastName());
        user.setEnabled(true);
        user.setEmailVerified(false);

        Map<String, List<String>> attributes = new HashMap<>();
        addAttribute(attributes, "gender", registerRequest.gender());
        addAttribute(attributes, "biography", registerRequest.biography());
        addAttribute(attributes, "phoneNumber", registerRequest.phoneNumber());
        user.setAttributes(attributes);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(registerRequest.password());
        credential.setTemporary(false);
        user.setCredentials(List.of(credential));

        UsersResource usersResource  = keycloak.realm(keycloakProperties.getRealm()).users();

        // Create the Keycloak user, then create the matching local profile row.
        try(Response response = usersResource.create(user)){
            log.info("Keycloak registration response status: {}", response.getStatus());
            if(response.getStatus() == HttpStatus.CREATED.value()){
                String createdUserId = CreatedResponseUtil.getCreatedId(response);
                UserRepresentation createdUser = usersResource.get(createdUserId).toRepresentation();

                UserProfile userProfile = new UserProfile();
                userProfile.setUserId(createdUserId);
                userProfile.setPhoneNumber(registerRequest.phoneNumber());
                userProfileRepository.save(userProfile);

                log.info("Created user profile for Keycloak user {}", createdUserId);
                return RegisterResponse.builder()
                        .keycloakUserId(createdUserId)
                        .username(createdUser.getUsername())
                        .email(createdUser.getEmail())
                        .firstName(createdUser.getFirstName())
                        .lastName(createdUser.getLastName())
                        .phoneNumber(createdUser.firstAttribute("phoneNumber"))
                        .gender(createdUser.firstAttribute("gender"))
                        .biography(createdUser.firstAttribute("biography"))
                        .build();
            }

            String details = response.hasEntity() ? response.readEntity(String.class) : "No details returned";
            throw new ResponseStatusException(
                    HttpStatusCode.valueOf(response.getStatus()),
                    "Keycloak could not create the user: " + details
            );
        }
    }

    private static void addAttribute(
            Map<String, List<String>> attributes,
            String name,
            String value
    ) {
        if (value != null && !value.isBlank()) {
            attributes.put(name, List.of(value));
        }
    }
}
