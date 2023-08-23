package com.mosamir.e_commerce.register.domain.repository

import com.mosamir.e_commerce.register.domain.model.RegisterRequest
import com.mosamir.e_commerce.register.domain.model.RegisterResponse
import com.mosamir.e_commerce.util.IResult

interface IRegisterRepo {

    suspend fun registerUser(registerRequest: RegisterRequest): IResult<RegisterResponse>

}