package com.d208.domain.model

import java.util.Date
import java.util.UUID

data class DomainTransaction(val id : UUID,
                             val content : String,
                             val transactionDate : Long,
                             val transactionType : String,
                             val category : String,
                             val amount : Int,
                             val deposit :Int,
                             val withdraw : Int)
