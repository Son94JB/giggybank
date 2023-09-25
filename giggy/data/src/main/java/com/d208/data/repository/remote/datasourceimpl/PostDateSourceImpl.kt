package com.d208.data.repository.remote.datasourceimpl

import com.d208.data.remote.api.PostApi
import com.d208.data.repository.remote.datasource.PostDateSource
import com.d208.data.utils.base.BaseDataSource
import javax.inject.Inject

class PostDateSourceImpl @Inject constructor(
    private val postApi: PostApi
) : BaseDataSource(), PostDateSource{
}