package com.d208.data.remote.api

import com.d208.data.remote.model.LoginData
import com.d208.data.remote.model.LoginUser
import com.d208.domain.model.DomainUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserApi {

    // 로그인
    @POST("user/login")
    suspend fun login(
        @Body data : LoginData,
    ) : Response<LoginUser>

    // 닉네임중복체크
    @POST("user/nickname")
    suspend fun duplicateNickNameCheck(
        @Body data :DomainUser,
    ) : Response<Boolean>
    //목표 소비액 변경
    @PUT("user/targetamount")
    suspend fun updateTargetAmount(
        @Body data : DomainUser,
    ) : Response<String>
    //회원가입
    @POST("user/signUp")
    suspend fun signUp(
        @Body data : DomainUser,

    ) : Response<String>

}