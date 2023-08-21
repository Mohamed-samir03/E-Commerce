package com.mosamir.e_commerce.login.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mosamir.e_commerce.login.domain.model.LoginRequest
import com.mosamir.e_commerce.login.domain.model.LoginResponse
import com.mosamir.e_commerce.login.domain.use_case.ILoginUseCase
import com.mosamir.e_commerce.util.IResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val iLoginUseCase: ILoginUseCase
):ViewModel() {

    val loginResult: MutableLiveData<IResult<LoginResponse>> = MutableLiveData()

    fun loginUser(email: String, pwd: String) {
        loginResult.value = IResult.Loading
        viewModelScope.launch {
            val loginRequest = LoginRequest(password = pwd, email = email)
            loginResult.value = iLoginUseCase.loginUser(loginRequest = loginRequest)
        }
    }

}