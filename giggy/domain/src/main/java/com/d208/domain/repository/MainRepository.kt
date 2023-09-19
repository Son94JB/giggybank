package com.d208.domain.repository

import com.d208.domain.model.DomainUser
import com.d208.domain.utils.RemoteErrorEmitter

interface MainRepository {
    suspend fun login(remoteErrorEmitter: RemoteErrorEmitter, accessToken : String, refreshToken : String, fcmToken : String) : DomainUser?
}