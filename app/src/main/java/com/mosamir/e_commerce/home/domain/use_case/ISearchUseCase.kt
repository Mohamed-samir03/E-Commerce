package com.mosamir.e_commerce.home.domain.use_case

import com.mosamir.e_commerce.home.domain.model.ProductResponse
import com.mosamir.e_commerce.home.domain.model.SearchRequest
import com.mosamir.e_commerce.util.IResult

interface ISearchUseCase {

    suspend fun searchProduct(searchRequest: SearchRequest): IResult<ProductResponse>

}