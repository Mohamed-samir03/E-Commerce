package com.mosamir.e_commerce.di

import android.content.Context
import com.mosamir.e_commerce.home.data.data_source.remote.IProductDataSource
import com.mosamir.e_commerce.home.data.repository.ProductRepo
import com.mosamir.e_commerce.home.domain.repository.IProductRepo
import com.mosamir.e_commerce.login.data.data_source.remote.ILoginDataSource
import com.mosamir.e_commerce.login.data.repository.LoginRepo
import com.mosamir.e_commerce.login.domain.repository.ILoginRepo
import com.mosamir.e_commerce.profile.data.data_source.remote.IProfileDataSource
import com.mosamir.e_commerce.profile.data.repository.ProfileRepo
import com.mosamir.e_commerce.profile.domain.repository.IProfileRepo
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

    @Provides
    fun provideProfileRepo(iProfileDataSource: IProfileDataSource):IProfileRepo
            = ProfileRepo(iProfileDataSource)

    @Provides
    fun provideProductRepo(iProductDataSource: IProductDataSource):IProductRepo
            = ProductRepo(iProductDataSource)

}