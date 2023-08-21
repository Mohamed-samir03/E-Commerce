package com.mosamir.e_commerce.login.data.data_source.remote

import com.mosamir.e_commerce.login.domain.model.LoginRequest
import com.mosamir.e_commerce.login.domain.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

const val LOGIN_USER = "login"

interface LoginApiService {

    @POST(LOGIN_USER)
    suspend fun loginUser(@Body loginRequest: LoginRequest): LoginResponse

}