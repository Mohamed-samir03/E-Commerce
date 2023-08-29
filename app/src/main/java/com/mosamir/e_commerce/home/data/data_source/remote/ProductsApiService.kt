package com.mosamir.e_commerce.home.data.data_source.remote

import com.mosamir.e_commerce.home.domain.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

const val PRODUCTS = "products"

interface ProductsApiService {

    @GET(PRODUCTS)
    suspend fun getProducts(@Header("Authorization") token: String): ProductResponse

}