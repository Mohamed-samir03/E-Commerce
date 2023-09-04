package com.mosamir.e_commerce.login.data.data_source.remote

import com.mosamir.e_commerce.login.domain.model.LoginRequest
import com.mosamir.e_commerce.login.domain.model.LoginResponse
import com.mosamir.e_commerce.util.IResult
import com.mosamir.e_commerce.util.NetworkState
import java.lang.Exception
import javax.inject.Inject

class LoginDataSource @Inject constructor(
    val loginApiService:LoginApiService
):ILoginDataSource {

    override suspend fun loginUser(loginRequest: LoginRequest): IResult<LoginResponse> {

        return try {
            val loginData = loginApiService.loginUser(loginRequest)
            IResult.onSuccess(loginData)
        }catch (e:Exception){
            IResult.onFail(NetworkState.getErrorMessage(e).msg.toString())
        }

    }
}