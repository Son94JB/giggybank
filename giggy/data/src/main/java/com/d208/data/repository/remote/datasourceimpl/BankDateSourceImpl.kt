package com.d208.data.repository.remote.datasourceimpl

import com.d208.data.remote.api.BankApi
import com.d208.data.remote.model.TransactionRequest
import com.d208.data.remote.model.TransactionResponse
import com.d208.data.repository.remote.datasource.BankDateSource
import com.d208.data.utils.base.BaseDataSource
import com.d208.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class BankDateSourceImpl @Inject constructor(
    private val bankApi : BankApi,
) : BaseDataSource(), BankDateSource{
    override suspend fun searchTransaction(
        remoteErrorEmitter: RemoteErrorEmitter,
        accountNumber: String,
        startDate: String,
        endDate: String
    ): MutableList<TransactionResponse>? {
        return safeApiCall(remoteErrorEmitter){
            val data = TransactionRequest(accountNumber, startDate, endDate)
            bankApi.searchTransaction(data)?.body()
        }

    }
}