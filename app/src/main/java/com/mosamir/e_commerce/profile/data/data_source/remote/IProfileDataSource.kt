package com.mosamir.e_commerce.profile.data.data_source.remote

import com.mosamir.e_commerce.profile.domain.model.ProfileResponse
import com.mosamir.e_commerce.profile.domain.model.UpdateProfileRequest
import com.mosamir.e_commerce.util.IResult
import retrofit2.http.Body
import retrofit2.http.Header

interface IProfileDataSource {

    suspend fun profileUser(token:String): IResult<ProfileResponse>

    suspend fun updateProfile(token:String, updateProfileRequest: UpdateProfileRequest): IResult<ProfileResponse>

}