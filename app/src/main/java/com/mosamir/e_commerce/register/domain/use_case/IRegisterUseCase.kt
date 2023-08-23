package com.mosamir.e_commerce.register.domain.use_case

import com.mosamir.e_commerce.register.domain.model.RegisterRequest
import com.mosamir.e_commerce.register.domain.model.RegisterResponse
import com.mosamir.e_commerce.util.IResult

interface IRegisterUseCase {

    suspend fun registerUser(registerRequest: RegisterRequest): IResult<RegisterResponse>

}