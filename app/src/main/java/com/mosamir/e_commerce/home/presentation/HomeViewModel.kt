package com.mosamir.e_commerce.home.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mosamir.e_commerce.home.domain.model.ProductResponse
import com.mosamir.e_commerce.home.domain.use_case.IGetProductsUseCase
import com.mosamir.e_commerce.util.IResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val iGetProductsUseCase: IGetProductsUseCase
): ViewModel() {

    val getProductResult: MutableLiveData<IResult<ProductResponse>> = MutableLiveData()

    fun getProducts(token:String) {
        getProductResult.value = IResult.Loading
        viewModelScope.launch {
            getProductResult.value = iGetProductsUseCase.getProducts(token)
        }
    }

}