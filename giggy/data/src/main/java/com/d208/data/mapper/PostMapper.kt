package com.d208.data.mapper

import com.d208.data.remote.model.PostResponse
import com.d208.domain.model.DomainPost

object PostMapper {

    fun postsMapper(
        response : List<PostResponse>?
    ) : MutableList<DomainPost> ? {
        return if(response != null){
            var list = mutableListOf<DomainPost>()
            for(data in response) {
                list.add(DomainPost(
                    id = data.id,
                    nickName = data.nickName,
                    createdAt = data.createAt,
                    postType = data.postType,
                    title = data.title,
                    category = data.category,
                    viewCount = data.viewCount,
                    likePost = data.isLiked,
                    likeCount = data.likeCnt,
                ))
            }
            list
        } else null
    }

}