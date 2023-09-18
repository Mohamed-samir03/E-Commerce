package com.mosamir.e_commerce.shopping.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mosamir.e_commerce.shopping.domain.model.SearchRequest
import com.mosamir.e_commerce.shopping.domain.use_case.IGetFavouritesUseCase
import com.mosamir.e_commerce.shopping.domain.use_case.IGetProductsUseCase
import com.mosamir.e_commerce.shopping.domain.use_case.ISearchUseCase
import com.mosamir.e_commerce.util.NetworkState
import com.mosamir.e_commerce.util.getError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val iGetProductsUseCase: IGetProductsUseCase,
    private val iSearchUseCase: ISearchUseCase,
    private val iGetFavouritesUseCase: IGetFavouritesUseCase
): ViewModel() {

    private val _getProductResult: MutableStateFlow<NetworkState?> = MutableStateFlow(null)
    val getProductResult: StateFlow<NetworkState?> =_getProductResult

    private val _searchProductResult: MutableStateFlow<NetworkState?> = MutableStateFlow(null)
    val searchProductResult: StateFlow<NetworkState?> =_searchProductResult

    private val _getFavouritesResult: MutableStateFlow<NetworkState?> = MutableStateFlow(null)
    val getFavouritesResult: StateFlow<NetworkState?> =_getFavouritesResult

    fun getProducts(token:String) {
        _getProductResult.value = NetworkState.LOADING
        viewModelScope.launch {
            try {
                val result = iGetProductsUseCase.getProducts(token)
                if (result.isSuccessful()){
                    _getProductResult.value = NetworkState.getLoaded(result)
                }else{
                    _getProductResult.value = NetworkState.getErrorMessage(result.getError().toString())
                }
            }catch (ex:Exception){
                ex.printStackTrace()
                _getProductResult.value = NetworkState.getErrorMessage(ex)
            }
        }
    }

    fun searchProducts(token: String,text:String) {
        _searchProductResult.value = NetworkState.LOADING
        viewModelScope.launch {
            try {
                val result = iSearchUseCase.searchProduct(token,SearchRequest(text))
                if (result.isSuccessful()){
                    _searchProductResult.value = NetworkState.getLoaded(result)
                }else{
                    _searchProductResult.value = NetworkState.getErrorMessage(result.getError().toString())
                }
            }catch (ex:Exception){
                ex.printStackTrace()
                _searchProductResult.value = NetworkState.getErrorMessage(ex)
            }
        }
    }


    fun getFavourites(token:String) {
        _getFavouritesResult.value = NetworkState.LOADING
        viewModelScope.launch {
            try {
                val result = iGetFavouritesUseCase.getFavourite(token)
                if (result.isSuccessful()){
                    _getFavouritesResult.value = NetworkState.getLoaded(result)
                }else{
                    _getFavouritesResult.value = NetworkState.getErrorMessage(result.getError().toString())
                }
            }catch (ex:Exception){
                ex.printStackTrace()
                _getFavouritesResult.value = NetworkState.getErrorMessage(ex)
            }
        }
    }


}