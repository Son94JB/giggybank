package com.d208.data.repository

import com.d208.data.mapper.MainMapper
import com.d208.data.repository.remote.datasource.MainDataSource
import com.d208.domain.model.DomainDuplicateCheck
import com.d208.domain.model.DomainUser
import com.d208.domain.repository.MainRepository
import com.d208.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainDataSource :MainDataSource

) : MainRepository {

    override suspend fun login(
        remoteErrorEmitter: RemoteErrorEmitter,
        accessToken: String,
        refreshToken: String,
        fcmToken: String
    ): DomainUser? {
        return MainMapper.loginMapper(mainDataSource.login(remoteErrorEmitter = remoteErrorEmitter, accessToken, refreshToken, fcmToken))
    }

    override suspend fun duplicateCheck(
        remoteErrorEmitter: RemoteErrorEmitter,
        checkText: String
    ): DomainDuplicateCheck? {
        return MainMapper.duplicateCheckMapper(mainDataSource.duplicateCheck(remoteErrorEmitter, checkText))
    }


}