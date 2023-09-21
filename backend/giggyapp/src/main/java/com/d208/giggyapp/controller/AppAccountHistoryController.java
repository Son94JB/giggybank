package com.d208.giggyapp.controller;

import com.d208.giggyapp.domain.AppAccountHistory;
import com.d208.giggyapp.dto.AppAccountHistory.AppAccountHistoryDto;
import com.d208.giggyapp.dto.AppAccountHistory.BankHistoryDTO;
import com.d208.giggyapp.dto.AppAccountHistory.NewAppAccountHistoryDTO;
import com.d208.giggyapp.repository.AppAccountHistoryRepository;
import com.d208.giggyapp.service.AppAccountHistoryService;
import com.fasterxml.jackson.databind.JsonSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/appaccounthistory")
public class AppAccountHistoryController {
    private final AppAccountHistoryService appAccountHistoryService;

    private final AppAccountHistoryRepository appAccountHistoryRepository;

    @PostMapping("/")
    public ResponseEntity<?> getAppAccountHistory(String accountNumber){
        // 은행으로부터 계좌거래내역 받아오기
        BankHistoryDTO BankHistory = appAccountHistoryService.getAppAccountHistory(accountNumber);

        // 장고로 거래내역을 보내고 분석한 데이터 받아오기
        AppAccountHistoryDto appAccountHistoryDto = appAccountHistoryService.analysisAppAccountHistory(BankHistory);
        // 분석한 내용 저장

        // 분석한 내용 반환
        return ResponseEntity.ok(true);
    }
}
