package com.mosamir.e_commerce.shopping.domain.use_case

import com.mosamir.e_commerce.shopping.domain.model.ProductResponse
import com.mosamir.e_commerce.shopping.domain.model.SearchRequest
import com.mosamir.e_commerce.shopping.domain.repository.IProductRepo
import com.mosamir.e_commerce.util.IResult
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val iProductRepo: IProductRepo
):ISearchUseCase {
    override suspend fun searchProduct(token: String,searchRequest: SearchRequest): IResult<ProductResponse> {
        return iProductRepo.searchProduct(token,searchRequest)
    }
}