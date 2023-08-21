package com.mosamir.e_commerce.login.data.repository

import com.mosamir.e_commerce.login.data.data_source.remote.ILoginDataSource
import com.mosamir.e_commerce.login.domain.model.LoginRequest
import com.mosamir.e_commerce.login.domain.model.LoginResponse
import com.mosamir.e_commerce.login.domain.repository.ILoginRepo
import com.mosamir.e_commerce.util.IResult
import javax.inject.Inject

class LoginRepo @Inject constructor(
    val iLoginDataSource: ILoginDataSource
):ILoginRepo {

    override suspend fun loginUser(loginRequest: LoginRequest): IResult<LoginResponse> {
        val loginData = iLoginDataSource.loginUser(loginRequest)
        if (loginData is IResult.Success){
            if (loginData.data.status){
                return loginData
            }else{
                return IResult.onFail(loginData.data.message)
            }
        }else{
            return loginData
        }
    }

}