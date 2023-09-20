package com.d208.data.remote.api

import com.d208.data.remote.model.LoginData
import com.d208.data.remote.model.LoginUser
import com.d208.data.remote.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("user/login")
    suspend fun login(
        @Body data : LoginData,
    ) : Response<LoginUser>

}