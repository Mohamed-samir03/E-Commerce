package com.mosamir.e_commerce.di

import android.content.Context
import com.mosamir.e_commerce.login.data.data_source.remote.ILoginDataSource
import com.mosamir.e_commerce.login.data.repository.LoginRepo
import com.mosamir.e_commerce.login.domain.repository.ILoginRepo
import com.mosamir.e_commerce.register.data.data_source.remote.IRegisterDataSource
import com.mosamir.e_commerce.register.data.repository.RegisterRepo
import com.mosamir.e_commerce.register.domain.repository.IRegisterRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    fun provideLoginRepo(iLoginDataSource: ILoginDataSource,@ApplicationContext context: Context):ILoginRepo
        = LoginRepo(iLoginDataSource,context)

    @Provides
    fun provideRegisterRepo(iRegisterDataSource: IRegisterDataSource,@ApplicationContext context: Context):IRegisterRepo
            = RegisterRepo(iRegisterDataSource,context)

}