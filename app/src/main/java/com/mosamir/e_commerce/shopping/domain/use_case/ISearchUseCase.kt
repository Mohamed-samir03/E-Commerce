package com.mosamir.e_commerce.shopping.domain.use_case

import com.mosamir.e_commerce.shopping.domain.model.ProductResponse
import com.mosamir.e_commerce.shopping.domain.model.SearchRequest
import com.mosamir.e_commerce.util.IResult

interface ISearchUseCase {

    suspend fun searchProduct(token: String,searchRequest: SearchRequest): IResult<ProductResponse>

}