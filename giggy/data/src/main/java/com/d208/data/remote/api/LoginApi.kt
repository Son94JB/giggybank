package com.d208.data.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("")
    suspend fun login(
        @Body accessToken : String,
    ) : Response<Boolean>

}