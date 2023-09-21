package com.d208.giggy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d208.domain.model.DomainUser
import com.d208.domain.usecase.GetUserUseCase
import com.d208.domain.utils.ErrorType
import com.d208.domain.utils.RemoteErrorEmitter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel(), RemoteErrorEmitter {

    private val _user = MutableLiveData<DomainUser>()

    var apiErrorType = ErrorType.UNKNOWN
    var errorMessage = "none"

    val user : LiveData<DomainUser> get() = _user

    fun getUserData(id : UUID){
        viewModelScope.launch {
            val user = DomainUser(id = id)
            getUserUseCase.execute(this@HomeFragmentViewModel, user).let {
                response ->
                _user.value = response
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