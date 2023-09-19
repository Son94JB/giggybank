package com.d208.domain.model

data class DomainLoginData (
    val accessToken: String,
    val fcmToken: String,
    val refreshToken: String,
)