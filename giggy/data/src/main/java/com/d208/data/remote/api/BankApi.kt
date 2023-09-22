package com.d208.data.remote.api

import com.d208.data.remote.model.TransactionRequest
import com.d208.data.remote.model.TransactionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.UUID

interface BankApi {

    @POST("/bank/search-transaction")
    suspend fun searchTransaction( @Body data : TransactionRequest) : Response<MutableList<TransactionResponse>>

    @POST("app/account-history/month")
    suspend fun searchMonths(@Body id : UUID) : Response<MutableList<String>>
}