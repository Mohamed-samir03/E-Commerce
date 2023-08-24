package com.mosamir.e_commerce.profile.data.data_source.remote

import com.mosamir.e_commerce.profile.domain.model.ProfileResponse
import com.mosamir.e_commerce.util.IResult
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
            IResult.onFail(e.localizedMessage)
        }
    }
}