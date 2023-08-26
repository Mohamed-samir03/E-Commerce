package com.mosamir.e_commerce.di

import com.mosamir.e_commerce.login.domain.repository.ILoginRepo
import com.mosamir.e_commerce.login.domain.use_case.ILoginUseCase
import com.mosamir.e_commerce.login.domain.use_case.LoginUseCase
import com.mosamir.e_commerce.profile.domain.repository.IProfileRepo
import com.mosamir.e_commerce.profile.domain.use_case.GetProfileUseCase
import com.mosamir.e_commerce.profile.domain.use_case.IGetProfileUseCase
import com.mosamir.e_commerce.profile.domain.use_case.IUpdateProfileUseCase
import com.mosamir.e_commerce.profile.domain.use_case.UpdateProfileUseCase
import com.mosamir.e_commerce.register.domain.repository.IRegisterRepo
import com.mosamir.e_commerce.register.domain.use_case.IRegisterUseCase
import com.mosamir.e_commerce.register.domain.use_case.RegisterUseCase
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

    @Provides
    fun provideRegisterUseCase(iRegisterRepo: IRegisterRepo):IRegisterUseCase = RegisterUseCase(iRegisterRepo)

    @Provides
    fun provideGetProfileUseCase(iProfileRepo: IProfileRepo):IGetProfileUseCase = GetProfileUseCase(iProfileRepo)

    @Provides
    fun provideUpdateProfileUseCase(iProfileRepo: IProfileRepo):IUpdateProfileUseCase = UpdateProfileUseCase(iProfileRepo)

}