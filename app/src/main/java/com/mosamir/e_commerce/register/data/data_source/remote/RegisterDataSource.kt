package com.mosamir.e_commerce.register.data.data_source.remote

import com.mosamir.e_commerce.register.domain.model.RegisterRequest
import com.mosamir.e_commerce.register.domain.model.RegisterResponse
import com.mosamir.e_commerce.util.IResult
import com.mosamir.e_commerce.util.NetworkState
import java.lang.Exception
import javax.inject.Inject

class RegisterDataSource @Inject constructor(
    private val registerApiService: RegisterApiService
):IRegisterDataSource {

    override suspend fun registerUser(registerRequest: RegisterRequest): IResult<RegisterResponse> {
        return try {
            val registerData = registerApiService.registerUser(registerRequest)
            IResult.onSuccess(registerData)
        }catch (e: Exception){
            IResult.onFail(NetworkState.getErrorMessage(e).msg.toString())
        }
    }

}