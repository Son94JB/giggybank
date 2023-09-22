package com.d208.data.repository.remote.datasource

import com.d208.data.remote.model.TransactionResponse
import com.d208.domain.utils.RemoteErrorEmitter

interface BankDateSource {

    suspend fun searchTransaction(
        remoteErrorEmitter: RemoteErrorEmitter,
        accountNumber : String,
        startDate : String,
        endDate : String,
    ) : MutableList<TransactionResponse>?
}