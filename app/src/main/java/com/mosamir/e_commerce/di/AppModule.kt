package com.mosamir.e_commerce.di

import android.content.Context
import com.mosamir.e_commerce.shopping.data.data_source.remote.ProductsApiService
import com.mosamir.e_commerce.login.data.data_source.remote.LoginApiService
import com.mosamir.e_commerce.profile.data.data_source.remote.ProfileApiService
import com.mosamir.e_commerce.register.data.data_source.remote.RegisterApiService
import com.mosamir.e_commerce.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofit(context: Context): Retrofit {
        val client = OkHttpClient.Builder()
            .connectTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
            .readTimeout(50, TimeUnit.SECONDS)
            .callTimeout(50, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideLoginApiService(retrofit: Retrofit):LoginApiService
            = retrofit.create(LoginApiService::class.java)

    @Provides
    @Singleton
    fun provideRegisterApiService(retrofit: Retrofit):RegisterApiService
            = retrofit.create(RegisterApiService::class.java)

    @Provides
    @Singleton
    fun provideProfileApiService(retrofit: Retrofit):ProfileApiService
            = retrofit.create(ProfileApiService::class.java)


    @Provides
    @Singleton
    fun provideProductsApiService(retrofit: Retrofit):ProductsApiService
            = retrofit.create(ProductsApiService::class.java)

}