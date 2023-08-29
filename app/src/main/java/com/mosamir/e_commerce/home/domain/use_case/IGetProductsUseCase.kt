package com.mosamir.e_commerce.home.domain.use_case

import com.mosamir.e_commerce.home.domain.model.ProductResponse
import com.mosamir.e_commerce.util.IResult

interface IGetProductsUseCase {

    suspend fun getProducts(token: String): IResult<ProductResponse>

}