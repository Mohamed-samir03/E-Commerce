package com.mosamir.e_commerce.profile.data.data_source.remote

import com.mosamir.e_commerce.profile.domain.model.ProfileResponse
import com.mosamir.e_commerce.util.IResult

interface IProfileDataSource {

    suspend fun profileUser(token:String): IResult<ProfileResponse>

}