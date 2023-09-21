package com.d208.giggy.viewmodel

import androidx.lifecycle.ViewModel
import com.d208.domain.model.DomainUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() :  ViewModel(){

    var user = DomainUser()
}