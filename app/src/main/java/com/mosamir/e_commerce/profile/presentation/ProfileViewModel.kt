package com.mosamir.e_commerce.profile.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mosamir.e_commerce.login.domain.model.LoginRequest
import com.mosamir.e_commerce.login.domain.model.LoginResponse
import com.mosamir.e_commerce.profile.domain.model.ProfileResponse
import com.mosamir.e_commerce.profile.domain.model.UpdateProfileRequest
import com.mosamir.e_commerce.profile.domain.use_case.IGetProfileUseCase
import com.mosamir.e_commerce.profile.domain.use_case.IUpdateProfileUseCase
import com.mosamir.e_commerce.util.IResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val iGetProfileUseCase: IGetProfileUseCase,
    private val iUpdateProfileUseCase: IUpdateProfileUseCase
):ViewModel() {

    val getProfileResult: MutableLiveData<IResult<ProfileResponse>> = MutableLiveData()

    val updateProfileResult: MutableLiveData<IResult<ProfileResponse>> = MutableLiveData()

    fun getProfileUser(token:String) {
        getProfileResult.value = IResult.Loading
        viewModelScope.launch {
            getProfileResult.value = iGetProfileUseCase.profileUser(token)
        }
    }

    fun updateProfileUser(token:String,updateProfileRequest: UpdateProfileRequest) {
        updateProfileResult.value = IResult.Loading
        viewModelScope.launch {
            updateProfileResult.value = iUpdateProfileUseCase.updateProfile(token,updateProfileRequest)
        }
    }

}