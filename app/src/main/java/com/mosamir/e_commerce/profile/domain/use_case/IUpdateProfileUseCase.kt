package com.mosamir.e_commerce.profile.domain.use_case

import com.mosamir.e_commerce.profile.domain.model.ProfileResponse
import com.mosamir.e_commerce.profile.domain.model.UpdateProfileRequest
import com.mosamir.e_commerce.util.IResult

interface IUpdateProfileUseCase {

    suspend fun updateProfile(token:String, updateProfileRequest: UpdateProfileRequest): IResult<ProfileResponse>

}