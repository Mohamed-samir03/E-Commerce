package com.mosamir.e_commerce.profile.domain.use_case

import com.mosamir.e_commerce.profile.domain.model.ProfileResponse
import com.mosamir.e_commerce.profile.domain.repository.IProfileRepo
import com.mosamir.e_commerce.util.IResult
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val iProfileRepo: IProfileRepo
):IGetProfileUseCase {

    override suspend fun profileUser(token: String): IResult<ProfileResponse> {
        return iProfileRepo.profileUser(token)
    }

}