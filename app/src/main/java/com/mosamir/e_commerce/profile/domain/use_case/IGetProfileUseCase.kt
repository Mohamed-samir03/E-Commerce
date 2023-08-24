package com.mosamir.e_commerce.profile.domain.use_case

import com.mosamir.e_commerce.profile.domain.model.ProfileResponse
import com.mosamir.e_commerce.util.IResult

interface IGetProfileUseCase {

    suspend fun profileUser(token:String): IResult<ProfileResponse>

}