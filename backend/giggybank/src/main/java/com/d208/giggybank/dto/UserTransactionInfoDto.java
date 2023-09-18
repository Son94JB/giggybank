package com.d208.giggybank.dto;

import lombok.Getter;

@Getter
public class UserTransactionInfoDto {

    String accountNumber;
    String transactionType;
    Integer dwamount;
    String content;

}
