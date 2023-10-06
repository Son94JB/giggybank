package com.d208.giggybank.data.datasource.model

import java.util.UUID

data class User(var id : UUID  = UUID.randomUUID(), var email : String = "", var nickname : String = "", var targetAmount : Int = 0, val currentAmount : Int = 0, var funToken : String ="", var refreshToken : String = "", var letLife : Int =0)