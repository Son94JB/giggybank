package com.d208.domain.repository

import com.d208.domain.model.DomainPost
import com.d208.domain.utils.RemoteErrorEmitter
import okhttp3.MultipartBody
import java.util.UUID

interface PostRepository {

    suspend fun registerPost(remoteErrorEmitter: RemoteErrorEmitter, id : UUID, title : String, content : String, postType : String, category : String, file : MultipartBody.Part?) : Long?

    suspend fun getPosts(remoteErrorEmitter: RemoteErrorEmitter, id : UUID) : MutableList<DomainPost>?
}