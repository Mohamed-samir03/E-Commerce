package com.mosamir.e_commerce.home.data.repository

import com.mosamir.e_commerce.home.data.data_source.remote.IProductDataSource
import com.mosamir.e_commerce.home.domain.model.ProductResponse
import com.mosamir.e_commerce.home.domain.model.SearchRequest
import com.mosamir.e_commerce.home.domain.repository.IProductRepo
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

    override suspend fun searchProduct(searchRequest: SearchRequest): IResult<ProductResponse> {
        val productData = iProductDataSource.searchProduct(searchRequest)
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

}