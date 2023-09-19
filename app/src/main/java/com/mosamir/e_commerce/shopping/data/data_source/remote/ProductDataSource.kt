package com.mosamir.e_commerce.shopping.data.data_source.remote

import com.mosamir.e_commerce.shopping.domain.model.ProductResponse
import com.mosamir.e_commerce.shopping.domain.model.SearchRequest
import com.mosamir.e_commerce.shopping.domain.model.adddelete.AddDeleteFavouriteResponse
import com.mosamir.e_commerce.shopping.domain.model.favourite.FavouriteResponse
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

    override suspend fun searchProduct(token: String,searchRequest: SearchRequest): IResult<ProductResponse> {
        return try {
            val productsData = productsApiService.searchProduct(token,searchRequest)
            IResult.onSuccess(productsData)
        }catch (e: Exception){
            IResult.onFail(NetworkState.getErrorMessage(e).msg.toString())
        }
    }

    override suspend fun getFavourite(token: String): IResult<FavouriteResponse> {
        return try {
            val favouritesData = productsApiService.getFavourite(token)
            IResult.onSuccess(favouritesData)
        }catch (e: Exception){
            IResult.onFail(NetworkState.getErrorMessage(e).msg.toString())
        }
    }

    override suspend fun addDeleteFavourite(
        token: String,
        productId: Int
    ): IResult<AddDeleteFavouriteResponse> {
        return try {
            val data = productsApiService.addDeleteFavourite(token,productId)
            IResult.onSuccess(data)
        }catch (e: Exception){
            IResult.onFail(NetworkState.getErrorMessage(e).msg.toString())
        }
    }
}