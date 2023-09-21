package com.d208.giggyapp.controller;


import com.d208.giggyapp.repository.AppAccountHistoryRepository;
import com.d208.giggyapp.service.AppAccountHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/appaccounthistory")
public class AppAccountHistoryController {
    private final AppAccountHistoryService appAccountHistoryService;

    private final AppAccountHistoryRepository appAccountHistoryRepository;

    @PostMapping("")
    public ResponseEntity<?> getAppAccountHistory(@RequestBody BankAccountDTO bankAccountDTO){
        String accountNumber = bankAccountDTO.getAccountNumber();
        UUID userId = bankAccountDTO.getUserId();
        // 은행으로부터 계좌거래내역 받아오기
        appAccountHistoryService.getAppAccountHistory(accountNumber, userId);
//        BankHistoryDTO bankHistoryDTO = BankHistoryDTO.builder().
//                id(0L).
//                amount(1000).
//                content("소고기").
//                transactionDate("2023-01-01T00:00:00").
//                transactionType("입금").
//                deposit(1000).
//                withdraw(1000).
//                build();

        // 장고로 거래내역을 보내고 분석한 데이터 받아오기
//        appAccountHistoryService.analysisAppAccountHistory(bankHistoryDTO, userId);
        // 분석한 내용 반환
        return ResponseEntity.ok(true);
    }

    @PostMapping("/receive")
    public ResponseEntity<?> getMonthAccountHistory(@RequestBody MonthDTO monthDTO){


        return ResponseEntity.ok(true);
    }
}
