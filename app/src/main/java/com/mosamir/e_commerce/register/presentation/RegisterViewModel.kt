package com.mosamir.e_commerce.register.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mosamir.e_commerce.login.domain.model.LoginRequest
import com.mosamir.e_commerce.login.domain.model.LoginResponse
import com.mosamir.e_commerce.register.domain.model.RegisterRequest
import com.mosamir.e_commerce.register.domain.model.RegisterResponse
import com.mosamir.e_commerce.register.domain.use_case.IRegisterUseCase
import com.mosamir.e_commerce.util.IResult
import com.mosamir.e_commerce.util.NetworkState
import com.mosamir.e_commerce.util.getError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val iRegisterUseCase: IRegisterUseCase
):ViewModel() {

    private val _registerResult: MutableStateFlow<NetworkState?> = MutableStateFlow(null)
    val registerResult: StateFlow<NetworkState?> =_registerResult

    fun registerUser(name: String, phone: String, email:String, password:String, image:String) {
        _registerResult.value = NetworkState.LOADING
        viewModelScope.launch {
            val registerRequest = RegisterRequest(name = name, phone = phone, email = email, password = password, image = image)
            try {
                val result = iRegisterUseCase.registerUser(registerRequest)
                if (result.isSuccessful()){
                    _registerResult.value = NetworkState.getLoaded(result)
                }else{
                    _registerResult.value = NetworkState.getErrorMessage(result.getError().toString())
                }
            }catch (ex:Exception){
                ex.printStackTrace()
                _registerResult.value = NetworkState.getErrorMessage(ex)
            }
        }
    }

}