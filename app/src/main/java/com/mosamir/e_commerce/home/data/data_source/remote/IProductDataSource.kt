package com.mosamir.e_commerce.home.data.data_source.remote

import com.mosamir.e_commerce.home.domain.model.ProductResponse
import com.mosamir.e_commerce.home.domain.model.SearchRequest
import com.mosamir.e_commerce.util.IResult

interface IProductDataSource {

    suspend fun getProducts(token: String): IResult<ProductResponse>

    suspend fun searchProduct(token: String,searchRequest: SearchRequest): IResult<ProductResponse>

}