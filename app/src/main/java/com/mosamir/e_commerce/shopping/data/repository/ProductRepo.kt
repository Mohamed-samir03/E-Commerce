package com.mosamir.e_commerce.shopping.data.repository

import com.mosamir.e_commerce.shopping.data.data_source.remote.IProductDataSource
import com.mosamir.e_commerce.shopping.domain.model.ProductResponse
import com.mosamir.e_commerce.shopping.domain.model.SearchRequest
import com.mosamir.e_commerce.shopping.domain.model.adddelete.AddDeleteFavouriteResponse
import com.mosamir.e_commerce.shopping.domain.model.favourite.FavouriteResponse
import com.mosamir.e_commerce.shopping.domain.repository.IProductRepo
import com.mosamir.e_commerce.util.IResult
import javax.inject.Inject

class ProductRepo @Inject constructor(
    private val iProductDataSource: IProductDataSource
):IProductRepo {

    override suspend fun getProducts(token: String): IResult<ProductResponse> {
        val productData = iProductDataSource.getProducts(token)
        if (productData is IResult.Success){
            if (productData.data.status){
                return productData
            }else{
                return IResult.onFail(productData.data.message.toString())
            }
        }else{
            return productData
        }
    }

    override suspend fun searchProduct(token: String,searchRequest: SearchRequest): IResult<ProductResponse> {
        val productData = iProductDataSource.searchProduct(token,searchRequest)
        if (productData is IResult.Success){
            if (productData.data.status){
                return productData
            }else{
                return IResult.onFail(productData.data.message.toString())
            }
        }else{
            return productData
        }
    }

    override suspend fun getFavourite(token: String): IResult<FavouriteResponse> {
        val favouritesData = iProductDataSource.getFavourite(token)
        if (favouritesData is IResult.Success){
            if (favouritesData.data.status){
                return favouritesData
            }else{
                return IResult.onFail(favouritesData.data.message.toString())
            }
        }else{
            return favouritesData
        }
    }

    override suspend fun addDeleteFavourite(
        token: String,
        productId: Int
    ): IResult<AddDeleteFavouriteResponse> {
        val data = iProductDataSource.addDeleteFavourite(token,productId)
        if (data is IResult.Success){
            if (data.data.status){
                return data
            }else{
                return IResult.onFail(data.data.message.toString())
            }
        }else{
            return data
        }
    }

}