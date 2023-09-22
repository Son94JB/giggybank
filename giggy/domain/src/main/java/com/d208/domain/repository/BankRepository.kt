package com.d208.domain.repository

import com.d208.domain.model.DomainTransaction
import com.d208.domain.utils.RemoteErrorEmitter

interface BankRepository {

    suspend fun searchTransaction(remoteErrorEmitter: RemoteErrorEmitter, accountNumber : String, startDate : String, endDate : String ): MutableList<DomainTransaction>?
}