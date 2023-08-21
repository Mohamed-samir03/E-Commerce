package com.mosamir.e_commerce.login.domain.use_case

import com.mosamir.e_commerce.login.domain.model.LoginRequest
import com.mosamir.e_commerce.login.domain.model.LoginResponse
import com.mosamir.e_commerce.util.IResult

interface ILoginUseCase {

    suspend fun loginUser(loginRequest: LoginRequest): IResult<LoginResponse>

}