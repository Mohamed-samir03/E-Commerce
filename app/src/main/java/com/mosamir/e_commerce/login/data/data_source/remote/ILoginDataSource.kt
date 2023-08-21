package com.mosamir.e_commerce.login.data.data_source.remote

import com.mosamir.e_commerce.login.domain.model.LoginRequest
import com.mosamir.e_commerce.login.domain.model.LoginResponse
import com.mosamir.e_commerce.util.IResult

interface ILoginDataSource {

    suspend fun loginUser(loginRequest: LoginRequest): IResult<LoginResponse>

}