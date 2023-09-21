package com.d208.giggybank.data.datasource.model

import java.util.UUID

data class Post(val id : Long,
                var USER_ID : UUID,
                var createdAt : Long,
                var modifeAt : Long,
                var title : String,
                var content : String,
                var viewCount : String,
                var picture : String,
                var postType : String,
                var category: String,)
