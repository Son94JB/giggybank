package com.d208.data.remote.api

import com.d208.data.remote.model.AnalysisRequest
import com.d208.data.remote.model.AnalysisResponse
import com.d208.data.remote.model.TransactionRequest
import com.d208.data.remote.model.TransactionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.UUID

interface BankApi {

    @POST("app/account-history/app")
    suspend fun searchTransaction(@Body data : TransactionRequest) : Response<List<TransactionResponse>>

    @POST("app/account-history/month")
    suspend fun searchMonths(@Body id : UUID) : Response<MutableList<String>>

    @POST("app/")
    suspend fun getAnalysis(@Body data : AnalysisRequest) : Response<MutableList<AnalysisResponse>>
}