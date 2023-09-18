package com.mosamir.e_commerce.shopping.domain.use_case

import com.mosamir.e_commerce.shopping.domain.model.favourite.FavouriteResponse
import com.mosamir.e_commerce.util.IResult

interface IGetFavouritesUseCase {

    suspend fun getFavourite(token: String): IResult<FavouriteResponse>

}