package com.d208.data.repository.remote.datasource

import com.d208.data.remote.model.PostResponse
import com.d208.domain.model.DomainPost
import com.d208.domain.utils.RemoteErrorEmitter
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import java.util.UUID

interface PostDataSource {

    suspend fun registerPost(remoteErrorEmitter: RemoteErrorEmitter,
                             id : UUID,
                             title : String,
                             content : String,
                             postType : String,
                             category : String,
                             file : MultipartBody.Part?
                             ) : Long?

    suspend fun getPosts(remoteErrorEmitter: RemoteErrorEmitter, id : UUID) : List<PostResponse>?

    suspend fun pushLike(remoteErrorEmitter: RemoteErrorEmitter, id : Long, userId : UUID) : Unit?
}