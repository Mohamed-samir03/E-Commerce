package com.mosamir.e_commerce.shopping.data.data_source.remote

import com.mosamir.e_commerce.shopping.domain.model.ProductResponse
import com.mosamir.e_commerce.shopping.domain.model.SearchRequest
import com.mosamir.e_commerce.shopping.domain.model.adddelete.AddDeleteFavouriteResponse
import com.mosamir.e_commerce.shopping.domain.model.favourite.FavouriteResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

const val PRODUCTS = "products"
const val SEARCH = "products/search"
const val FAVOURITES = "favorites"

interface ProductsApiService {

    @GET(PRODUCTS)
    suspend fun getProducts(@Header("Authorization") token: String): ProductResponse

    @POST(SEARCH)
    suspend fun searchProduct(@Header("Authorization") token: String,
                              @Body searchRequest: SearchRequest
    ):ProductResponse

    @GET(FAVOURITES)
    suspend fun getFavourite(@Header("Authorization") token: String): FavouriteResponse

    @POST(FAVOURITES)
    @FormUrlEncoded
    suspend fun addDeleteFavourite(@Header("Authorization") token: String,
                                    @Field("product_id") productId : Int) : AddDeleteFavouriteResponse

}