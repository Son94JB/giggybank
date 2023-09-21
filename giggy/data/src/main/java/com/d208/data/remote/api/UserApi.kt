package com.d208.data.remote.api

import android.accounts.Account
import com.d208.data.remote.model.AccountAuthData
import com.d208.data.remote.model.AccountAuthResponse
import com.d208.data.remote.model.LoginData
import com.d208.data.remote.model.LoginUser
import com.d208.domain.model.DomainUser
import com.d208.domain.model.SignUpUser
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
    @POST("user/signup")
    suspend fun signUp(
        @Body data : SignUpUser,

    ) : Response<Boolean>

    //계좌 인증
    @POST("bank/auth")
    suspend fun accountAuth(
        @Body data : AccountAuthData,
    ) : Response<AccountAuthResponse>
}