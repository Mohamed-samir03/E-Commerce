package com.mosamir.e_commerce.home.domain.repository

import com.mosamir.e_commerce.home.domain.model.ProductResponse
import com.mosamir.e_commerce.home.domain.model.SearchRequest
import com.mosamir.e_commerce.util.IResult

interface IProductRepo {

    suspend fun getProducts(token: String): IResult<ProductResponse>

    suspend fun searchProduct(searchRequest: SearchRequest): IResult<ProductResponse>

}