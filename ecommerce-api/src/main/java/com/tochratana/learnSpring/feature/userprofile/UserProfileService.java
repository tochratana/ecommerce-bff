package com.tochratana.learnSpring.feature.userprofile;

import com.tochratana.learnSpring.feature.userprofile.dto.PatchUserProfileRequest;
import com.tochratana.learnSpring.feature.userprofile.dto.UserProfileResponse;

public interface UserProfileService {

    UserProfileResponse patchUserProfile(PatchUserProfileRequest request);

    UserProfileResponse getUserProfile();
}
