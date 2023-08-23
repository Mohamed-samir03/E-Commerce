package com.mosamir.e_commerce.di

import com.mosamir.e_commerce.login.data.data_source.remote.ILoginDataSource
import com.mosamir.e_commerce.login.data.data_source.remote.LoginApiService
import com.mosamir.e_commerce.login.data.data_source.remote.LoginDataSource
import com.mosamir.e_commerce.register.data.data_source.remote.IRegisterDataSource
import com.mosamir.e_commerce.register.data.data_source.remote.RegisterApiService
import com.mosamir.e_commerce.register.data.data_source.remote.RegisterDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {


    @Provides
    fun getLoginDataSource(apiService: LoginApiService):ILoginDataSource = LoginDataSource(apiService)


    @Provides
    fun getRegisterDataSource(apiService: RegisterApiService):IRegisterDataSource = RegisterDataSource(apiService)

}