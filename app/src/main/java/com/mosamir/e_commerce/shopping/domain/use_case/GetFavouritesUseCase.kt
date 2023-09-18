package com.mosamir.e_commerce.shopping.domain.use_case

import com.mosamir.e_commerce.shopping.domain.model.favourite.FavouriteResponse
import com.mosamir.e_commerce.shopping.domain.repository.IProductRepo
import com.mosamir.e_commerce.util.IResult
import javax.inject.Inject

class GetFavouritesUseCase @Inject constructor(
    private val iProductRepo: IProductRepo
) :IGetFavouritesUseCase {
    override suspend fun getFavourite(token: String): IResult<FavouriteResponse> {
        return iProductRepo.getFavourite(token)
    }
}