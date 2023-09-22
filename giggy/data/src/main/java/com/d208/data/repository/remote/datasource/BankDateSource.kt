package com.d208.data.repository.remote.datasource

import com.d208.data.remote.model.TransactionResponse
import com.d208.domain.utils.RemoteErrorEmitter
import java.util.UUID

interface BankDateSource {

    suspend fun searchTransaction(
        remoteErrorEmitter: RemoteErrorEmitter,
        accountNumber : String,
        startDate : String,
        endDate : String,
    ) : MutableList<TransactionResponse>?

    suspend fun searchMonths(
        remoteErrorEmitter: RemoteErrorEmitter,
        id : UUID,
    ) : MutableList<String>?
}