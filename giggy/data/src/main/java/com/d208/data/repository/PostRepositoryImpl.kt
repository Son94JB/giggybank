package com.d208.data.repository

import com.d208.data.mapper.PostMapper
import com.d208.data.repository.remote.datasource.PostDataSource
import com.d208.domain.model.DomainPost
import com.d208.domain.model.DomainPostDetail
import com.d208.domain.repository.PostRepository
import com.d208.domain.utils.RemoteErrorEmitter
import okhttp3.MultipartBody
import java.util.UUID
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postDataSource: PostDataSource
) : PostRepository{
    override suspend fun registerPost(
        remoteErrorEmitter: RemoteErrorEmitter,
        id: UUID,
        title: String,
        content: String,
        postType: String,
        category: String,
        file : MultipartBody.Part?
    ): Long? {
        return postDataSource.registerPost(remoteErrorEmitter, id, title, content, postType, category, file)
    }

    override suspend fun getPosts(
        remoteErrorEmitter: RemoteErrorEmitter,
        id: UUID
    ): MutableList<DomainPost>? {
        return PostMapper.postsMapper(postDataSource.getPosts(remoteErrorEmitter, id))
    }

    override suspend fun pushLike(remoteErrorEmitter: RemoteErrorEmitter, id: Long, userId: UUID): Unit? {
        return postDataSource.pushLike(remoteErrorEmitter, id, userId)
    }

    override suspend fun getOnePost(
        remoteErrorEmitter: RemoteErrorEmitter,
        id: Long,
        userId: UUID
    ): DomainPostDetail? {
        return PostMapper.onePostMapper(postDataSource.getOnePost(remoteErrorEmitter, id, userId))
    }


}