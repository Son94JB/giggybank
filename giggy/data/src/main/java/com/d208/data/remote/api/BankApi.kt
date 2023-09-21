package com.d208.data.remote.api

import com.d208.data.remote.model.TransactionRequest
import com.d208.data.remote.model.TransactionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface BankApi {

    @POST("/bank/search-transaction")
    suspend fun searchTransaction( @Body data : TransactionRequest) : Response<MutableList<TransactionResponse>>
}