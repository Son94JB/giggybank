package com.d208.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.d208.data.mapper.BankMapper
import com.d208.data.repository.remote.datasource.BankDateSource
import com.d208.domain.model.DomainTransaction
import com.d208.domain.repository.BankRepository
import com.d208.domain.utils.RemoteErrorEmitter
import java.util.UUID
import javax.inject.Inject

class BankRepositoryImpl @Inject constructor(
    private val bankDateSource: BankDateSource
) : BankRepository{
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun searchTransaction(
        remoteErrorEmitter: RemoteErrorEmitter,
        accountNumber: String,
        startDate: String,
        endDate: String
    ): MutableList<DomainTransaction>? {
       return BankMapper.searchTransactionMapper(bankDateSource.searchTransaction(remoteErrorEmitter, accountNumber, startDate, endDate))
    }

    override suspend fun searchMonths(
        remoteErrorEmitter: RemoteErrorEmitter,
        id: UUID
    ): MutableList<String>? {
        return BankMapper.searchMonths(bankDateSource.searchMonths(remoteErrorEmitter, id))
    }
}