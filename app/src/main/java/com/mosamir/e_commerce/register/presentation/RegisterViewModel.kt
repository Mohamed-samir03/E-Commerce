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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val iRegisterUseCase: IRegisterUseCase
):ViewModel() {

    val registerResult: MutableLiveData<IResult<RegisterResponse>> = MutableLiveData()

    fun registerUser(name: String, phone: String, email:String, password:String, image:String) {
        registerResult.value = IResult.Loading
        viewModelScope.launch {
            val registerRequest = RegisterRequest(name = name, phone = phone, email = email, password = password, image = image)
            registerResult.value = iRegisterUseCase.registerUser(registerRequest = registerRequest)
        }
    }

}