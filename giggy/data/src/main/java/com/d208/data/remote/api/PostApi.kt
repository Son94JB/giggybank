package com.d208.data.remote.api

import com.d208.data.remote.model.PostRequest
import com.d208.data.remote.model.PostResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import java.util.UUID

interface PostApi {
    @Multipart
    @POST("app/post")
    suspend fun registerPost(@Part("postCreateDto") data : PostRequest, @Part file : MultipartBody.Part?) : Response<Long>

    @GET("app/post/{userId}")
    suspend fun getPosts(@Path("userId") id : UUID) : Response<List<PostResponse>>

    @GET("app/post/{postId}/{userId}")
    suspend fun getOnePost(@Path("postId") id : Long, @Path("userId") userId : UUID) : Response<PostResponse>
}