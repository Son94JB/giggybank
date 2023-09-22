package com.d208.giggyapp.controller;


import com.d208.giggyapp.dto.AppAccountHistory.BankAccountDTO;
import com.d208.giggyapp.dto.AppAccountHistory.MonthDTO;
import com.d208.giggyapp.repository.AppAccountHistoryRepository;
import com.d208.giggyapp.service.AppAccountHistoryService;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/app")
public class AppAccountHistoryController {
    private final AppAccountHistoryService appAccountHistoryService;

    private final AppAccountHistoryRepository appAccountHistoryRepository;

    @PostMapping("/account-history")
    public ResponseEntity<?> getAppAccountHistory(@RequestBody BankAccountDTO bankAccountDTO){
        // 은행으로부터 계좌거래내역 받아오기
        String accountNumber = bankAccountDTO.getAccountNumber();
        UUID userId = bankAccountDTO.getUserId();
        appAccountHistoryService.getAppAccountHistory(accountNumber, userId);
        // 분석한 내용 반환
        return ResponseEntity.ok(true);
    }

    @PostMapping("/month")
    public ResponseEntity<?> getMonthAccountHistory(@RequestBody MonthDTO monthDTO){
        UUID userId = monthDTO.getUserId();
        String month = monthDTO.getMonth();
        return ResponseEntity.ok(true);
    }

    @PutMapping("/category")
    public ResponseEntity<?> updateAccountHistory(@PathVariable Long id, @RequestBody String category){
        appAccountHistoryService.updateCategory(id, category);
        return ResponseEntity.ok().build();
    }
}
