package com.d208.data.mapper

import android.util.Log
import com.d208.data.remote.model.LoginUser
import com.d208.domain.model.DomainUser

private const val TAG = "MainMapper giggy"
object MainMapper {


    fun loginMapper(
        response: LoginUser?
    ): DomainUser? {
        Log.d(TAG, "loginMapper: $response")
        return if (response != null ) {
            Log.d(TAG, "loginMapper: null 아님")

            DomainUser(
                id = response.id,
                email = response.email,
                nickname = response.nickname,
                targetAmount = response.targetAmount,
                fcmToken = response.fcmToken,
                refreshToken = response.refreshToken,
                leftLife = response.leftLife,
            )

        } else response
    }
}