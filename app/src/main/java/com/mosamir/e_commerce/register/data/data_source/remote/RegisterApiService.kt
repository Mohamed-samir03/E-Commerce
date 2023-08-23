package com.mosamir.e_commerce.register.data.data_source.remote

import com.mosamir.e_commerce.register.domain.model.RegisterRequest
import com.mosamir.e_commerce.register.domain.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

const val REGISTER_USER = "register"

interface RegisterApiService {

    @POST(REGISTER_USER)
    suspend fun registerUser(@Body registerRequest: RegisterRequest):RegisterResponse

}