package com.d208.giggy.view

import androidx.lifecycle.ViewModel
import com.d208.domain.utils.ErrorType
import com.d208.domain.utils.RemoteErrorEmitter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameFragmentViewModel @Inject constructor() : ViewModel(), RemoteErrorEmitter {
    override fun onError(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onError(errorType: ErrorType) {
        TODO("Not yet implemented")
    }
}