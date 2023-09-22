package com.d208.giggybank.ui

import androidx.lifecycle.ViewModel
import com.d208.giggybank.data.datasource.model.User
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class MainActivityVIewModel : ViewModel() {

    val user = User()
}