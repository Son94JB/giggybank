package com.d208.giggy.viewmodel

import android.provider.ContactsContract.CommonDataKinds.Nickname
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d208.domain.model.DomainDuplicateCheck
import com.d208.domain.model.DomainUser
import com.d208.domain.usecase.DuplicateCheckUsecase
import com.d208.domain.utils.ErrorType
import com.d208.domain.utils.RemoteErrorEmitter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpFragmentViewModel @Inject constructor(
    private val duplicateCheckUsecase: DuplicateCheckUsecase,
) : ViewModel(), RemoteErrorEmitter {

    var apiErrorType = ErrorType.UNKNOWN
    var errorMessage = "none"

    private val _checkSuccess = MutableLiveData<DomainDuplicateCheck> ()

    val checkSuccess : LiveData<DomainDuplicateCheck> get() = _checkSuccess

    fun duplicateNickNameCheck(user : DomainUser, nickname: String){
        viewModelScope.launch {
            user.nickname = nickname
            duplicateCheckUsecase.execute(this@SignUpFragmentViewModel, user).let {
                response -> _checkSuccess.value = response
            }
        }
    }
    override fun onError(msg: String) {
        errorMessage = msg
    }

    override fun onError(errorType: ErrorType) {
        apiErrorType = errorType
    }


}