package com.d208.data.repository

import com.d208.data.mapper.MainMapper
import com.d208.data.repository.remote.datasource.MainDataSource
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


}