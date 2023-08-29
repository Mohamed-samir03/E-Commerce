package com.mosamir.e_commerce.home.data.data_source.remote

import com.mosamir.e_commerce.home.domain.model.ProductResponse
import com.mosamir.e_commerce.util.IResult
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
            IResult.onFail(e.localizedMessage)
        }
    }
}