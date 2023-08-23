package com.mosamir.e_commerce.register.data.data_source.remote

import com.mosamir.e_commerce.register.domain.model.RegisterRequest
import com.mosamir.e_commerce.register.domain.model.RegisterResponse
import com.mosamir.e_commerce.util.IResult

interface IRegisterDataSource {

    suspend fun registerUser(registerRequest: RegisterRequest): IResult<RegisterResponse>

}