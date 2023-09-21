package com.d208.giggy.di


import com.d208.data.repository.BankRepositoryImpl
import com.d208.data.repository.MainRepositoryImpl
import com.d208.data.repository.remote.datasourceimpl.BankDateSourceImpl
import com.d208.data.repository.remote.datasourceimpl.MainDataSourceImpl
import com.d208.domain.repository.BankRepository
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

    @Provides
    @Singleton
    fun provideBankRepository(
        bankDataSourceImpl : BankDateSourceImpl
    ): BankRepository {
        return BankRepositoryImpl(
            bankDataSourceImpl
        )
    }

}