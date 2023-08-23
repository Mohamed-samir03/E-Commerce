package com.mosamir.e_commerce.register.data.repository

import android.content.Context
import com.mosamir.e_commerce.register.data.data_source.remote.IRegisterDataSource
import com.mosamir.e_commerce.register.domain.model.RegisterRequest
import com.mosamir.e_commerce.register.domain.model.RegisterResponse
import com.mosamir.e_commerce.register.domain.repository.IRegisterRepo
import com.mosamir.e_commerce.util.IResult
import com.mosamir.e_commerce.util.SessionManager
import javax.inject.Inject

class RegisterRepo @Inject constructor(
    private val iRegisterDataSource: IRegisterDataSource,
    private val context: Context
):IRegisterRepo {
    override suspend fun registerUser(registerRequest: RegisterRequest): IResult<RegisterResponse> {
        val registerData = iRegisterDataSource.registerUser(registerRequest)
        return if (registerData is IResult.Success){
            if (registerData.data.status){
                SessionManager.saveAuthToken(context,registerData.data.data.token)
                registerData
            }else{
                IResult.onFail(registerData.data.message)
            }
        }else{
            registerData
        }
    }
}