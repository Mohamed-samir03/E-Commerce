package com.mosamir.e_commerce.profile.domain.use_case

import com.mosamir.e_commerce.profile.domain.model.ProfileResponse
import com.mosamir.e_commerce.profile.domain.model.UpdateProfileRequest
import com.mosamir.e_commerce.profile.domain.repository.IProfileRepo
import com.mosamir.e_commerce.util.IResult
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(
    private val iProfileRepo: IProfileRepo
):IUpdateProfileUseCase {
    override suspend fun updateProfile(
        token: String,
        updateProfileRequest: UpdateProfileRequest
    ): IResult<ProfileResponse> {
        return iProfileRepo.updateProfile(token,updateProfileRequest)
    }
}