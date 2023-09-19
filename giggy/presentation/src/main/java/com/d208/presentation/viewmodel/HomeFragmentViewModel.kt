package com.d208.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor() : ViewModel() {

//    private val _user = MutableLiveData<User>()
//
//    val user : LiveData<User> get() = _user

    fun getUserData(id : UUID){

    }
}