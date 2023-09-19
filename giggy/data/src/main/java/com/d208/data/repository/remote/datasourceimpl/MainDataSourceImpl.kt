package com.d208.data.repository.remote.datasourceimpl

import android.util.Log
import com.d208.data.remote.api.LoginApi
import com.d208.data.remote.model.LoginData
import com.d208.data.remote.model.LoginUser
import com.d208.data.remote.model.User
import com.d208.data.repository.remote.datasource.MainDataSource
import com.d208.data.utils.base.BaseDataSource
import com.d208.domain.utils.RemoteErrorEmitter
import retrofit2.Response


import javax.inject.Inject

private const val TAG = "MainDataSourceImpl giggy"
class MainDataSourceImpl @Inject constructor(
    private val loginApi: LoginApi,

) : BaseDataSource(), MainDataSource {

    override suspend fun login(remoteErrorEmitter: RemoteErrorEmitter, accessToken : String, refreshToken : String, fcmToken : String): LoginUser? {
        return safeApiCall(remoteErrorEmitter){
            val data = LoginData(accessToken = accessToken, refreshToken= refreshToken, fcmToken = fcmToken)
            loginApi.login(data)
        ?.body() }
    }


}