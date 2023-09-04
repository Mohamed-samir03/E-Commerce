package com.mosamir.e_commerce.login.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mosamir.e_commerce.login.domain.model.LoginRequest
import com.mosamir.e_commerce.login.domain.model.LoginResponse
import com.mosamir.e_commerce.login.domain.use_case.ILoginUseCase
import com.mosamir.e_commerce.util.IResult
import com.mosamir.e_commerce.util.NetworkState
import com.mosamir.e_commerce.util.getError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val iLoginUseCase: ILoginUseCase
):ViewModel() {

    private val _loginResult: MutableStateFlow<NetworkState?> = MutableStateFlow(null)
    val loginResult: StateFlow<NetworkState?> =_loginResult

    fun loginUser(email: String, pwd: String) {
        _loginResult.value = NetworkState.LOADING
        viewModelScope.launch {
            val loginRequest = LoginRequest(password = pwd, email = email)
            try {
                val result = iLoginUseCase.loginUser(loginRequest)
                if (result.isSuccessful()){
                    _loginResult.value = NetworkState.getLoaded(result)
                }else{
                    _loginResult.value = NetworkState.getErrorMessage(result.getError().toString())
                }
            }catch (ex:Exception){
                ex.printStackTrace()
                _loginResult.value = NetworkState.getErrorMessage(ex)
            }
        }
    }

}