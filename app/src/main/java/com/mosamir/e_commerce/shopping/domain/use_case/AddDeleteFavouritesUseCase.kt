package com.mosamir.e_commerce.shopping.domain.use_case

import com.mosamir.e_commerce.shopping.domain.model.adddelete.AddDeleteFavouriteResponse
import com.mosamir.e_commerce.shopping.domain.repository.IProductRepo
import com.mosamir.e_commerce.util.IResult
import javax.inject.Inject

class AddDeleteFavouritesUseCase @Inject constructor(
    private val iProductRepo: IProductRepo
)  :IAddDeleteFavouritesUseCase{
    override suspend fun addDeleteFavourite(
        token: String,
        productId: Int
    ): IResult<AddDeleteFavouriteResponse> {
        return iProductRepo.addDeleteFavourite(token,productId)
    }
}