package com.d208.data.mapper

import com.d208.data.remote.model.LoginUser
import com.d208.domain.model.DomainUser

object MainMapper {


    fun loginMapper(
        response: LoginUser?
    ): DomainUser? {
        return if (response != null ) {
             response.id?.let {
                DomainUser(
                    id = it,
                    email = response.email,
                    nickname = response.nickname,
                    targetAmount = response.targetAmount,
                    fcmToken = response.fcmToken,
                    refreshToken = response.refreshToken,
                    leftLife = response.leftLife,
                )
            }
        } else response
    }
}