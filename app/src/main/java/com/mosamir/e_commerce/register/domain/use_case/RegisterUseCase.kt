package com.mosamir.e_commerce.register.domain.use_case

import com.mosamir.e_commerce.register.domain.model.RegisterRequest
import com.mosamir.e_commerce.register.domain.model.RegisterResponse
import com.mosamir.e_commerce.register.domain.repository.IRegisterRepo
import com.mosamir.e_commerce.util.IResult
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val iRegisterRepo: IRegisterRepo
):IRegisterUseCase {
    override suspend fun registerUser(registerRequest: RegisterRequest): IResult<RegisterResponse> {
        return iRegisterRepo.registerUser(registerRequest)
    }

}