package com.mosamir.e_commerce.login.domain.repository

import com.mosamir.e_commerce.login.domain.model.LoginRequest
import com.mosamir.e_commerce.login.domain.model.LoginResponse
import com.mosamir.e_commerce.util.IResult

interface ILoginRepo {

    suspend fun loginUser(loginRequest: LoginRequest): IResult<LoginResponse>

}