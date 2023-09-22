package com.d208.domain.usecase

import com.d208.domain.repository.BankRepository
import com.d208.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class TransactionUsecase @Inject constructor(
    private val bankRepository: BankRepository
) {
    suspend fun execute(remoteErrorEmitter: RemoteErrorEmitter, accountNumber : String, startDate : String, endDate : String) = bankRepository.searchTransaction(remoteErrorEmitter, accountNumber, startDate, endDate)
}