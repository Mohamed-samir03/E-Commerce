package com.mosamir.e_commerce.login.domain.use_case

import com.mosamir.e_commerce.login.domain.model.LoginRequest
import com.mosamir.e_commerce.login.domain.model.LoginResponse
import com.mosamir.e_commerce.login.domain.repository.ILoginRepo
import com.mosamir.e_commerce.util.IResult
import javax.inject.Inject

class LoginUseCase @Inject constructor(
     val iLoginRepo: ILoginRepo
):ILoginUseCase {
    override suspend fun loginUser(loginRequest: LoginRequest): IResult<LoginResponse> {
        return iLoginRepo.loginUser(loginRequest)
    }
}