package com.d208.giggy.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d208.domain.usecase.GetMonthsUsecase
import com.d208.domain.utils.ErrorType
import com.d208.domain.utils.RemoteErrorEmitter
import com.d208.giggy.di.App
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

private const val TAG = "ConsumeAnalysisFragment giggy"
@HiltViewModel
class ConsumeAnalysisFragmentViewModel @Inject constructor(
    private val searchMonthsUsecase: GetMonthsUsecase
) : ViewModel(), RemoteErrorEmitter{
    private val _monthList = MutableLiveData<MutableList<String>> ()
    val monthList : LiveData<MutableList<String>> get() = _monthList


    fun searchMonths(){
        viewModelScope.launch {
            searchMonthsUsecase.execute(this@ConsumeAnalysisFragmentViewModel,
                UUID.fromString(App.sharedPreferences.getString("id")!!)).let {
                    response ->

                    Log.d(TAG, "searchMonths: $response")
                if (response != null) {
                    _monthList.value = response.reversed() as MutableList<String>
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