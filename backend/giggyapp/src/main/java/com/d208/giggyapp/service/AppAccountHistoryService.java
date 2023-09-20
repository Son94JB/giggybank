package com.d208.giggyapp.service;

import com.d208.giggyapp.dto.AppAccountHistory.AppAccountHistoryDto;
import com.d208.giggyapp.dto.AppAccountHistory.BankHistoryDTO;
import com.d208.giggyapp.dto.AppAccountHistory.NewAppAccountHistoryDTO;
import com.d208.giggyapp.repository.AppAccountHistoryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AppAccountHistoryService {
    private final AppAccountHistoryRepository appAccountHistoryRepository;

    // 은행으로부터 거래내역 받아오기
    public BankHistoryDTO getAppAccountHistory(String accountNumber){
        String url = "http://localhost:8082/api/v1/bank/search-transaction";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{\"accountNumber\" : \"" + accountNumber + "\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<BankHistoryDTO> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, BankHistoryDTO.class);
        BankHistoryDTO responseBody = response.getBody();
        return responseBody;
    }

    // 장고서버로 거래내역 전송후 분석한 데이터 받아옴
    public AppAccountHistoryDto analysisAppAccountHistory(BankHistoryDTO BankHistory) {
        String url = "http://localhost:8081/api/v1/analysis";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        BankHistoryDTO requestBody = BankHistory;
        HttpEntity<BankHistoryDTO> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<AppAccountHistoryDto> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, AppAccountHistoryDto.class);
        AppAccountHistoryDto responseBody = response.getBody();
        return responseBody;
    }
}
