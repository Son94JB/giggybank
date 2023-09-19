package com.d208.data.repository.remote.datasource

import com.d208.data.remote.model.LoginUser
import com.d208.data.remote.model.User
import com.d208.domain.utils.RemoteErrorEmitter
import retrofit2.Response

interface MainDataSource {
    suspend fun login(
        remoteErrorEmitter: RemoteErrorEmitter,
        accessToken: String,
        refreshToken : String,
        fcmToken : String,
        ): LoginUser?
}