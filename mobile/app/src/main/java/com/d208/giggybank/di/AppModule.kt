package com.d208.giggybank.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
//    val API_URL = "http://localhost:8080/"
//
//    @Singleton
//    @Provides
//    fun getRetroServiceInstacne(retrofit : Retrofit) : RetroServiceInstance {
//        return retrofit.create(RetroServiceInstance::class.java)
//    }
//
//    @Singleton
//    @Provides
//    fun getRetroInstance() : Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(API_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
}