package com.d208.domain.repository

import com.d208.domain.model.DomainTransaction
import com.d208.domain.utils.RemoteErrorEmitter
import java.util.UUID

interface BankRepository {

    suspend fun searchTransaction(remoteErrorEmitter: RemoteErrorEmitter, accountNumber : String, startDate : String, endDate : String ): MutableList<DomainTransaction>?

    suspend fun searchMonths(remoteErrorEmitter: RemoteErrorEmitter, id : UUID) : MutableList<String>?
}