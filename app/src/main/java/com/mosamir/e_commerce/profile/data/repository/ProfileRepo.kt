package com.mosamir.e_commerce.profile.data.repository

import com.mosamir.e_commerce.profile.data.data_source.remote.IProfileDataSource
import com.mosamir.e_commerce.profile.domain.model.ProfileResponse
import com.mosamir.e_commerce.profile.domain.model.UpdateProfileRequest
import com.mosamir.e_commerce.profile.domain.repository.IProfileRepo
import com.mosamir.e_commerce.util.IResult
import javax.inject.Inject

class ProfileRepo @Inject constructor(
    private val iProfileDataSource: IProfileDataSource
) :IProfileRepo {
    override suspend fun profileUser(token: String): IResult<ProfileResponse> {
        val profileData = iProfileDataSource.profileUser(token)
        if (profileData is IResult.Success){
            if (profileData.data.status){
                return profileData
            }else{
                return IResult.onFail(profileData.data.message.toString())
            }
        }else{
            return profileData
        }
    }

    override suspend fun updateProfile(
        token: String,
        updateProfileRequest: UpdateProfileRequest
    ): IResult<ProfileResponse> {
        val profileData = iProfileDataSource.updateProfile(token,updateProfileRequest)
        if (profileData is IResult.Success){
            if (profileData.data.status){
                return profileData
            }else{
                return IResult.onFail(profileData.data.message.toString())
            }
        }else{
            return profileData
        }
    }
}