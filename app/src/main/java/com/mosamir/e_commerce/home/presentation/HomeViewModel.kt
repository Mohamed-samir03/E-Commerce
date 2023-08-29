package com.mosamir.e_commerce.home.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mosamir.e_commerce.home.domain.model.ProductResponse
import com.mosamir.e_commerce.home.domain.model.SearchRequest
import com.mosamir.e_commerce.home.domain.use_case.IGetProductsUseCase
import com.mosamir.e_commerce.home.domain.use_case.ISearchUseCase
import com.mosamir.e_commerce.util.IResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val iGetProductsUseCase: IGetProductsUseCase,
    private val iSearchUseCase: ISearchUseCase
): ViewModel() {

    val getProductResult: MutableLiveData<IResult<ProductResponse>> = MutableLiveData()

    val searchProductResult: MutableLiveData<IResult<ProductResponse>> = MutableLiveData()

    fun getProducts(token:String) {
        getProductResult.value = IResult.Loading
        viewModelScope.launch {
            getProductResult.value = iGetProductsUseCase.getProducts(token)
        }
    }

    fun searchProducts(text:String) {
        searchProductResult.value = IResult.Loading
        viewModelScope.launch {
            searchProductResult.value = iSearchUseCase.searchProduct(SearchRequest(text))
        }
    }


}