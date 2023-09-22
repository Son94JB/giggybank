package com.d208.giggybank.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d208.giggybank.data.datasource.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

private const val TAG = "LoginFragmentViewModel_giggy"
@HiltViewModel
class LoginFragmentViewModel : ViewModel() {
    private val _loginSuccess = MutableLiveData<Boolean> ()
    val loginSuccess : LiveData<Boolean> get() = _loginSuccess

    val user = User()

    fun login(access : String){

        viewModelScope.launch {
            try{

                Log.d(TAG, "login 성공")
            } catch (e : Exception){
                Log.d(TAG, "login 실패 :  ${e.message}")
            }
        }

    }
}