package com.d208.data.mapper

import android.util.Log
import com.d208.data.remote.model.AccountAuthResponse
import com.d208.data.remote.model.LoginUser
import com.d208.domain.model.DomainDuplicateCheck
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
                birthday = response.birthday,
            )

        } else response
    }

    fun getUseDataMapper(
        response: LoginUser?
    ) : DomainUser? {
        Log.d(TAG, "getUseDataMapper: $response")
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
                birthday = response.birthday,
                currentAmount = response.currentAmount
            )
        } else response
    }

    fun duplicateCheckMapper(
        response: Boolean?
    ) : DomainDuplicateCheck? {
        return if(response != null){
            DomainDuplicateCheck(
                duplicate = response,
            )
        } else response
    }

    fun signUpMapper(
        response: Boolean?
    ) : Boolean? {
        return response ?: false
    }

    fun accountAuthMapper(response: AccountAuthResponse
    ) : String? {
        return if(response.type == 2){
            response.content
        } else "서버 오류"
    }
}