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
import com.mosamir.e_commerce.util.NetworkState
import com.mosamir.e_commerce.util.getError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val iGetProfileUseCase: IGetProfileUseCase,
    private val iUpdateProfileUseCase: IUpdateProfileUseCase
):ViewModel() {

    private val _getProfileResult: MutableStateFlow<NetworkState?> = MutableStateFlow(null)
    val getProfileResult: StateFlow<NetworkState?> =_getProfileResult

    private val _updateProfileResult: MutableStateFlow<NetworkState?> = MutableStateFlow(null)
    val updateProfileResult: StateFlow<NetworkState?> =_updateProfileResult

    fun getProfileUser(token:String) {
        _getProfileResult.value = NetworkState.LOADING
        viewModelScope.launch {
            try {
                val result = iGetProfileUseCase.profileUser(token)
                if (result.isSuccessful()){
                    _getProfileResult.value = NetworkState.getLoaded(result)
                }else{
                    _getProfileResult.value = NetworkState.getErrorMessage(result.getError().toString())
                }
            }catch (ex:Exception){
                ex.printStackTrace()
                _getProfileResult.value = NetworkState.getErrorMessage(ex)
            }
        }
    }

    fun updateProfileUser(token:String,updateProfileRequest: UpdateProfileRequest) {
        _updateProfileResult.value = NetworkState.LOADING
        viewModelScope.launch {
            try {
                val result = iUpdateProfileUseCase.updateProfile(token,updateProfileRequest)
                if (result.isSuccessful()){
                    _updateProfileResult.value = NetworkState.getLoaded(result)
                }else{
                    _updateProfileResult.value = NetworkState.getErrorMessage(result.getError().toString())
                }
            }catch (ex:Exception){
                ex.printStackTrace()
                _updateProfileResult.value = NetworkState.getErrorMessage(ex)
            }
        }
    }

}