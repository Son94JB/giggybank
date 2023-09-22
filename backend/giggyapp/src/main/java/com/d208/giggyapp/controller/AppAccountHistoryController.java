package com.d208.giggyapp.controller;


import com.d208.giggyapp.dto.AppAccountHistory.*;
import com.d208.giggyapp.repository.AppAccountHistoryRepository;
import com.d208.giggyapp.service.AppAccountHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/app")
public class AppAccountHistoryController {
    private final AppAccountHistoryService appAccountHistoryService;

    private final AppAccountHistoryRepository appAccountHistoryRepository;

    // 은행거래내역 받아오고 분석
    @PostMapping("/account-history/bank")
    public ResponseEntity<?> getAppAccountHistory(@RequestBody BankAccountDTO bankAccountDTO){
        // 은행으로부터 계좌거래내역 받아오기
        String accountNumber = bankAccountDTO.getAccountNumber();
        UUID userId = bankAccountDTO.getUserId();
        appAccountHistoryService.getAppAccountHistory(accountNumber, userId);
        // 분석한 내용 반환
        return ResponseEntity.ok(true);
    }

    // 거래내역 조회 할 수 있는 달
    @PostMapping("/account-history/month")
    public ResponseEntity<List<String>> getMonthAccountHistory(@RequestBody UUID userId){
        List<String> yearMonths = appAccountHistoryService.getMonth(userId);
        return ResponseEntity.ok(yearMonths);
    }

    // 특정 거래내역 카테고리 수정
    @PutMapping("/account-history/app/category")
    public ResponseEntity<?> updateAccountHistory(@RequestBody AccountHistoryDTO accountHistoryDTO){
        Long id = accountHistoryDTO.getId();
        String category = accountHistoryDTO.getCategory();
        appAccountHistoryService.updateCategory(id, category);
        return ResponseEntity.ok().build();
    }

    // 거래내역 조회
    @PostMapping("/account-history/app")
    public ResponseEntity<List<AccountHistoryDTO>> getAccountHistory(@RequestBody DateDTO accountHistoryDateDTO){
        List<AccountHistoryDTO> accountHistoryDTOS = appAccountHistoryService.getAppAccountHistory(accountHistoryDateDTO);
        return ResponseEntity.ok(accountHistoryDTOS);
    }

    // 분석내역 조회
    @PostMapping("/account-history/app/analysis")
    public  ResponseEntity<?> getAnalysis(@RequestBody MonthDTO monthDTO){
        List<AnalysisDTO> analysisDTOS = appAccountHistoryService.getAnalysis(monthDTO);
        return ResponseEntity.ok().build();
    }
}
