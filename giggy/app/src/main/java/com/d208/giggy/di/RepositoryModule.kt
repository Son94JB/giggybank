package com.d208.giggy.di

import com.d208.data.repository.MainRepositoryImpl
import com.d208.data.repository.remote.datasource.MainDataSource
import com.d208.data.repository.remote.datasourceimpl.MainDataSourceImpl
import com.d208.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(
        mainDataSourceImpl : MainDataSourceImpl
    ): MainRepository {
        return MainRepositoryImpl(
            mainDataSourceImpl
        )
    }


}