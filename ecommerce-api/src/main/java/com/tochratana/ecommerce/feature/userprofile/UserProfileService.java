package com.tochratana.ecommerce.feature.userprofile;

import com.tochratana.ecommerce.feature.userprofile.dto.PatchUserProfileRequest;
import com.tochratana.ecommerce.feature.userprofile.dto.UserProfileResponse;

public interface UserProfileService {

    UserProfileResponse patchUserProfile(PatchUserProfileRequest request);

    UserProfileResponse getUserProfile();
}
