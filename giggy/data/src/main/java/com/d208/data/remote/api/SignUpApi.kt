package com.d208.data.remote.api

import com.d208.data.remote.model.DuplicateCheck
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApi {

    @POST("")
    suspend fun duplicateNickNameCheck(
        @Body data :String,
    ) : Response<Boolean>
}