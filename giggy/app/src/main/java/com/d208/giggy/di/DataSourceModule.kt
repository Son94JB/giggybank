package com.d208.giggy.di

import com.d208.data.remote.api.BankApi
import com.d208.data.remote.api.PostApi
import com.d208.data.remote.api.UserApi
import com.d208.data.repository.remote.datasource.BankDateSource
import com.d208.data.repository.remote.datasource.MainDataSource
import com.d208.data.repository.remote.datasource.PostDateSource
import com.d208.data.repository.remote.datasourceimpl.BankDateSourceImpl
import com.d208.data.repository.remote.datasourceimpl.MainDataSourceImpl
import com.d208.data.repository.remote.datasourceimpl.PostDateSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideMainDataSource(
        userApi: UserApi,
    ) : MainDataSource {
        return MainDataSourceImpl(
            userApi,
            )
    }

    @Provides
    @Singleton
    fun provideBankDateSource(
        bankApi : BankApi,
    ) : BankDateSource{
        return BankDateSourceImpl(
            bankApi,
        )
    }

    @Provides
    @Singleton
    fun providePostDateSource(
        postApi : PostApi,
    ) : PostDateSource {
        return PostDateSourceImpl(
            postApi,
        )
    }
}