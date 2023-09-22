package com.d208.giggybank.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.d208.giggybank.data.datasource.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID

@HiltViewModel
class HomeFragmentViewModel : ViewModel() {

    private val _user = MutableLiveData<User>()

    val user : LiveData<User> get() = _user

    fun getUserData(id : UUID){

    }
}