package com.mosamir.e_commerce.shopping.domain.use_case

import com.mosamir.e_commerce.shopping.domain.model.ProductResponse
import com.mosamir.e_commerce.util.IResult

interface IGetProductsUseCase {

    suspend fun getProducts(token: String): IResult<ProductResponse>

}