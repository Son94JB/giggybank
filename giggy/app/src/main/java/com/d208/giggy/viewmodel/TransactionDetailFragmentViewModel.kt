package com.d208.giggy.viewmodel

import androidx.lifecycle.ViewModel
import com.d208.domain.utils.ErrorType
import com.d208.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class TransactionDetailFragmentViewModel @Inject constructor(

) : ViewModel(), RemoteErrorEmitter{

    fun updateCategory(id : Long, category : String){

    }

    override fun onError(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onError(errorType: ErrorType) {
        TODO("Not yet implemented")
    }
}