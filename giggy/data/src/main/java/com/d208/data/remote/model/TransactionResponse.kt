package com.d208.data.remote.model

import java.time.LocalDateTime
import java.util.UUID

data class TransactionResponse( val id : UUID,
                                val amount : Int,
                               val transactionDate : LocalDateTime,
                               val transactionType : String,
                               val deposit : Int, val withdraw :Int,
                               val content : String,
                               val category : String,
)

