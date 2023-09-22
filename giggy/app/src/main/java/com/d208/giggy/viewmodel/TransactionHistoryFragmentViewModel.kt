package com.d208.giggy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d208.domain.model.DomainTransaction
import com.d208.domain.usecase.TransactionUsecase
import com.d208.domain.utils.ErrorType
import com.d208.domain.utils.RemoteErrorEmitter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionHistoryFragmentViewModel @Inject constructor(
    private val transactionUsecase: TransactionUsecase
) :  ViewModel(), RemoteErrorEmitter {
    var apiErrorType = ErrorType.UNKNOWN
    var errorMessage = "none"

    private val _transactionList = MutableLiveData<MutableList<DomainTransaction>>()
    val transactionList : LiveData<MutableList<DomainTransaction>> get() =_transactionList
    fun getTransactionData(accountNumber : String, startDate : String, endDate : String){
        viewModelScope.launch {
            transactionUsecase.execute(this@TransactionHistoryFragmentViewModel, accountNumber, startDate, endDate).let{
                response ->
                if(response != null){
                    _transactionList.value = response.sortedByDescending { it.transactionDate } as MutableList<DomainTransaction>
                }
                else{
                    _transactionList.value = mutableListOf()
                }
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