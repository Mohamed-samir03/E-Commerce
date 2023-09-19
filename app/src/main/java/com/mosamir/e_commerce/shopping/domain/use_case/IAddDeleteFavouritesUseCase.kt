package com.mosamir.e_commerce.shopping.domain.use_case

import com.mosamir.e_commerce.shopping.domain.model.adddelete.AddDeleteFavouriteResponse
import com.mosamir.e_commerce.util.IResult

interface IAddDeleteFavouritesUseCase {

    suspend fun addDeleteFavourite(token: String,productId : Int) : IResult<AddDeleteFavouriteResponse>
}