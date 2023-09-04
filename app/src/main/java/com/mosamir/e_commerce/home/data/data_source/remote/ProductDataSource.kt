package com.mosamir.e_commerce.home.data.data_source.remote

import com.mosamir.e_commerce.home.domain.model.ProductResponse
import com.mosamir.e_commerce.home.domain.model.SearchRequest
import com.mosamir.e_commerce.util.IResult
import com.mosamir.e_commerce.util.NetworkState
import java.lang.Exception
import javax.inject.Inject

class ProductDataSource @Inject constructor(
    private val productsApiService: ProductsApiService
):IProductDataSource {
    override suspend fun getProducts(token: String): IResult<ProductResponse> {
        return try {
            val productsData = productsApiService.getProducts(token)
            IResult.onSuccess(productsData)
        }catch (e: Exception){
            IResult.onFail(NetworkState.getErrorMessage(e).msg.toString())
        }
    }

    override suspend fun searchProduct(searchRequest: SearchRequest): IResult<ProductResponse> {
        return try {
            val productsData = productsApiService.searchProduct(searchRequest)
            IResult.onSuccess(productsData)
        }catch (e: Exception){
            IResult.onFail(NetworkState.getErrorMessage(e).msg.toString())
        }
    }
}