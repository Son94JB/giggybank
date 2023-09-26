package com.d208.giggy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d208.domain.model.DomainPostDetail
import com.d208.domain.usecase.GetOnePostUsecase
import com.d208.domain.utils.ErrorType
import com.d208.domain.utils.RemoteErrorEmitter
import com.d208.giggy.di.App
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CommunityPostDetailFragmentViewModel @Inject constructor(
    private val getOnePostUsecase: GetOnePostUsecase
): ViewModel(), RemoteErrorEmitter{

    private val _post = MutableLiveData<DomainPostDetail>()
    val post : LiveData<DomainPostDetail> get() = _post


    fun getOnePost(id : Long){
        viewModelScope.launch {
            getOnePostUsecase.execute(this@CommunityPostDetailFragmentViewModel, id, UUID.fromString(
                App.sharedPreferences.getString("id"))).let {
                    response ->
                if(response != null){
                    _post.value  = response
                }

            }
        }
    }




    var apiErrorType = ErrorType.UNKNOWN
    var errorMessage = "none"

    private val _exceptionHandler = MutableLiveData<Int>()
    val exceptionHandler : LiveData<Int> get() = _exceptionHandler
    override fun onError(msg: String) {
        errorMessage = msg
    }

    override fun onError(errorType: ErrorType) {
        apiErrorType = errorType

        when (errorType) {
            ErrorType.NETWORK -> {
                // 네트워크 에러 처리
                _exceptionHandler.value = 0
            }
            ErrorType.SESSION_EXPIRED -> {
                // 세션 만료 에러 처리
                _exceptionHandler.value = 401
            }
            // 다른 에러 유형에 대한 처리 추가
            else -> {
                _exceptionHandler.value = 4
            }
        }
    }


}