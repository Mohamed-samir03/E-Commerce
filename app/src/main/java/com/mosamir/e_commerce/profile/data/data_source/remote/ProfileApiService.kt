package com.mosamir.e_commerce.profile.data.data_source.remote

import com.mosamir.e_commerce.profile.domain.model.ProfileResponse
import retrofit2.http.GET
import retrofit2.http.Header


const val PROFILE_USER = "profile"

interface ProfileApiService {

    @GET(PROFILE_USER)
    suspend fun profileUser(@Header("Authorization") token:String):ProfileResponse

}