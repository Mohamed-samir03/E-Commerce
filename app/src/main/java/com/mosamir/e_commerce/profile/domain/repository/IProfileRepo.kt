package com.mosamir.e_commerce.profile.domain.repository

import com.mosamir.e_commerce.profile.domain.model.ProfileResponse
import com.mosamir.e_commerce.util.IResult

interface IProfileRepo {

    suspend fun profileUser(token:String): IResult<ProfileResponse>

}