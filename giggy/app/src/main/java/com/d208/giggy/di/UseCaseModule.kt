package com.d208.giggy.di

import com.d208.domain.repository.BankRepository
import com.d208.domain.repository.MainRepository
import com.d208.domain.usecase.DuplicateCheckUsecase
import com.d208.domain.usecase.LoginUsecase
import com.d208.domain.usecase.TransactionUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideLoginUseCase(repository : MainRepository) =  LoginUsecase(repository)

    @Provides
    @Singleton
    fun provideDuplicateUseCase(repository : MainRepository) =  DuplicateCheckUsecase(repository)

    @Provides
    @Singleton
    fun provideTransactionUseCase(repository: BankRepository) = TransactionUsecase(repository)
}