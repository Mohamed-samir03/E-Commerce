package com.mosamir.e_commerce.profile.data.data_source.remote

import com.mosamir.e_commerce.profile.domain.model.ProfileResponse
import com.mosamir.e_commerce.profile.domain.model.UpdateProfileRequest
import com.mosamir.e_commerce.util.IResult
import com.mosamir.e_commerce.util.NetworkState
import java.lang.Exception
import javax.inject.Inject

class ProfileDataSource @Inject constructor(
    private val profileApiService: ProfileApiService
):IProfileDataSource {
    override suspend fun profileUser(token: String): IResult<ProfileResponse> {
        return try {
            val profileData = profileApiService.profileUser(token)
            IResult.onSuccess(profileData)
        }catch (e: Exception){
            IResult.onFail(NetworkState.getErrorMessage(e).msg.toString())
        }
    }

    override suspend fun updateProfile(
        token: String,
        updateProfileRequest: UpdateProfileRequest
    ): IResult<ProfileResponse> {
        return try {
            val profileData = profileApiService.updateProfile(token,updateProfileRequest)
            IResult.onSuccess(profileData)
        }catch (e: Exception){
            IResult.onFail(NetworkState.getErrorMessage(e).msg.toString())
        }
    }
}