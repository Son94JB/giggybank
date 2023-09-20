package com.d208.domain.model

import java.util.Date
import java.util.UUID

data class DomainTransaction(var id : UUID, var content : String, var transactionDate : Long, var transactionType : String, var category : String, var amount : Int)
