package com.mosamir.e_commerce.di

import com.mosamir.e_commerce.login.domain.repository.ILoginRepo
import com.mosamir.e_commerce.login.domain.use_case.ILoginUseCase
import com.mosamir.e_commerce.login.domain.use_case.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UdeCaseModule {

    @Provides
    fun provideLoginUseCase(iLoginRepo: ILoginRepo):ILoginUseCase = LoginUseCase(iLoginRepo)

}