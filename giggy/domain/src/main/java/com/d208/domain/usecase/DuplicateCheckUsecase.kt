package com.d208.domain.usecase

import com.d208.domain.repository.MainRepository
import com.d208.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class DuplicateCheckUsecase @Inject constructor(
    private val mainRepository: MainRepository
){
    suspend fun execute(remoteErrorEmitter: RemoteErrorEmitter, checkText : String) = mainRepository.duplicateCheck(remoteErrorEmitter, checkText)
}