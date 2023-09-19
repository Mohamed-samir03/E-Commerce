package com.mosamir.e_commerce.shopping.data.data_source.remote

import com.mosamir.e_commerce.shopping.domain.model.ProductResponse
import com.mosamir.e_commerce.shopping.domain.model.SearchRequest
import com.mosamir.e_commerce.shopping.domain.model.adddelete.AddDeleteFavouriteResponse
import com.mosamir.e_commerce.shopping.domain.model.favourite.FavouriteResponse
import com.mosamir.e_commerce.util.IResult

interface IProductDataSource {

    suspend fun getProducts(token: String): IResult<ProductResponse>

    suspend fun searchProduct(token: String,searchRequest: SearchRequest): IResult<ProductResponse>

    suspend fun getFavourite(token: String): IResult<FavouriteResponse>

    suspend fun addDeleteFavourite(token: String,productId : Int) : IResult<AddDeleteFavouriteResponse>

}