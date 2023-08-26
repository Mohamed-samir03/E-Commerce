package com.mosamir.e_commerce.profile.data.data_source.remote

import com.mosamir.e_commerce.profile.domain.model.ProfileResponse
import com.mosamir.e_commerce.profile.domain.model.UpdateProfileRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT


const val PROFILE_USER = "profile"
const val UPDATE_PROFILE = "update-profile"

interface ProfileApiService {

    @GET(PROFILE_USER)
    suspend fun profileUser(@Header("Authorization") token:String):ProfileResponse

    @PUT(UPDATE_PROFILE)
    suspend fun updateProfile(
        @Header("Authorization") token:String,
        @Body updateProfileRequest: UpdateProfileRequest
    ):ProfileResponse

}