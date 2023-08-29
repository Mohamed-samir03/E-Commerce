package com.mosamir.e_commerce.home.domain.use_case

import com.mosamir.e_commerce.home.domain.model.ProductResponse
import com.mosamir.e_commerce.home.domain.repository.IProductRepo
import com.mosamir.e_commerce.util.IResult
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val iProductRepo: IProductRepo
):IGetProductsUseCase {
    override suspend fun getProducts(token: String): IResult<ProductResponse> {
        return iProductRepo.getProducts(token)
    }
}