package com.tochratana.learnSpring.feature.userprofile;

import com.tochratana.learnSpring.feature.userprofile.dto.PatchUserProfileRequest;
import com.tochratana.learnSpring.feature.userprofile.dto.UserProfileResponse;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public abstract class UserProfileMapper {

    public void updateUserRepresentation(
            UserRepresentation userRepresentation,
            PatchUserProfileRequest request
    ) {
        if (request == null) {
            return;
        }

        if (request.firstName() != null) {
            userRepresentation.setFirstName(request.firstName());
        }
        if (request.lastName() != null) {
            userRepresentation.setLastName(request.lastName());
        }

        Map<String, List<String>> attributes = copyAttributes(userRepresentation.getAttributes());
        setAttribute(attributes, "gender", request.gender());
        setAttribute(attributes, "biography", request.biography());
        setAttribute(attributes, "phoneNumber", request.phoneNumber());
        userRepresentation.setAttributes(attributes);
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "userId", ignore = true)
    public abstract void updateEntity(
            @MappingTarget UserProfile userProfile,
            PatchUserProfileRequest request
    );

    public UserProfileResponse toResponse(
            UserRepresentation userRepresentation,
            UserProfile userProfile
    ) {
        String phoneNumber = userProfile.getPhoneNumber() != null
                ? userProfile.getPhoneNumber()
                : userRepresentation.firstAttribute("phoneNumber");

        return UserProfileResponse.builder()
                .userId(userRepresentation.getId())
                .email(userRepresentation.getEmail())
                .firstName(userRepresentation.getFirstName())
                .lastName(userRepresentation.getLastName())
                .gender(userRepresentation.firstAttribute("gender"))
                .biography(userRepresentation.firstAttribute("biography"))
                .profilePicture(userProfile.getProfilePicture())
                .jobTitle(userProfile.getJobTitle())
                .salary(userProfile.getSalary())
                .phoneNumber(phoneNumber)
                .githubLink(userProfile.getGithubLink())
                .facebookLink(userProfile.getFacebookLink())
                .build();
    }

    private static Map<String, List<String>> copyAttributes(
            Map<String, List<String>> originalAttributes
    ) {
        Map<String, List<String>> attributes = new HashMap<>();
        if (originalAttributes != null) {
            originalAttributes.forEach((name, values) ->
                    attributes.put(name, values == null ? new ArrayList<>() : new ArrayList<>(values)));
        }
        return attributes;
    }

    private static void setAttribute(
            Map<String, List<String>> attributes,
            String name,
            String value
    ) {
        if (value != null) {
            attributes.put(name, List.of(value));
        }
    }
}
