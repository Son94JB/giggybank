package com.d208.data.repository

import com.d208.data.repository.remote.datasource.PostDateSource
import com.d208.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postDateSource: PostDateSource
) : PostRepository{
}