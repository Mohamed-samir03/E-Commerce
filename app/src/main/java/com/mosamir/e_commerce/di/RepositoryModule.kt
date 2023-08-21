package com.mosamir.e_commerce.di

import com.mosamir.e_commerce.login.data.data_source.remote.ILoginDataSource
import com.mosamir.e_commerce.login.data.repository.LoginRepo
import com.mosamir.e_commerce.login.domain.repository.ILoginRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    fun provideLoginRepo(iLoginDataSource: ILoginDataSource):ILoginRepo = LoginRepo(iLoginDataSource)

}