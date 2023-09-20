package com.d208.data.remote.api

import com.d208.data.remote.model.LoginData
import com.d208.data.remote.model.LoginUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    // 로그인
    @POST("user/login")
    suspend fun login(
        @Body data : LoginData,
    ) : Response<LoginUser>

    // 닉네임중복체크
    @POST("")
    suspend fun duplicateNickNameCheck(
        @Body data :String,
    ) : Response<Boolean>

}