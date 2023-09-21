package com.d208.giggyapp.service;

import com.d208.giggyapp.domain.AppAccountHistory;
import com.d208.giggyapp.domain.User;
import com.d208.giggyapp.dto.AppAccountHistory.AppAccountHistoryDto;
import com.d208.giggyapp.dto.AppAccountHistory.BankAccountDTO;
import com.d208.giggyapp.dto.AppAccountHistory.BankHistoryDTO;
import com.d208.giggyapp.repository.AppAccountHistoryRepository;
import com.d208.giggyapp.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class AppAccountHistoryService {
    private final AppAccountHistoryRepository appAccountHistoryRepository;
    private final UserRepository userRepository;

    // 은행으로부터 거래내역 받아오기
    public Void getAppAccountHistory(String accountNumber, UUID userId){
        //        String url = "http://127.0.0.1:8082/api/v1/bank/search-transaction";
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        String requestBody = "{\"accountNumber\" : \"" + accountNumber + "\"}";
//        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
//
//        ResponseEntity<BankHistoryDTO> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, BankHistoryDTO.class);
//        BankHistoryDTO responseBody = response.getBody();


        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        LocalDateTime startDate;
        LocalDateTime endDate;

        // 거래내역이 있는지 확인
        Optional<AppAccountHistory> optionalAppAccountHistory = appAccountHistoryRepository.findFirstByUserOrderByTransactionDate(user);
        if(optionalAppAccountHistory.isPresent()){
            AppAccountHistory appAccountHistory = optionalAppAccountHistory.get();
            startDate = appAccountHistory.getTransactionDate();
            endDate = LocalDateTime.now();
        }else{
            startDate = LocalDateTime.now().withDayOfMonth(1);
            endDate = LocalDateTime.now();
        }

        // 은행 데이터 받아오고 분석
        String url = "http://127.0.0.1:8000/api/v1/analysis/receive";
        RestTemplate restTemplate = new RestTemplate();


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();

        String requestBody = "{\"accountNumber\": \"" + accountNumber + "\", " +
                "\"startDate\": \"" + startDate + "\", " +
                "\"endDate\": \"" + endDate + "\"}";

        HttpEntity<?> requestEntity = new HttpEntity<>(requestBody, headers);
        try{
            ResponseEntity<AppAccountHistoryDto> response = restTemplate.exchange(uri.toString(), HttpMethod.POST, requestEntity, AppAccountHistoryDto.class);
            AppAccountHistoryDto appAccountHistoryDto = response.getBody();
            List<AppAccountHistoryDto.DataBody> dataList = appAccountHistoryDto.getData();
            System.out.println(dataList);

            // 정보 저장
            int tmpAmount = 0;
            for(AppAccountHistoryDto.DataBody data : dataList) {
                AppAccountHistory appAccountHistory = AppAccountHistory.builder().
                        transactionType(data.getTransactionType()).
                        transactionDate(data.getTransactionDate()).
                        content(data.getContent()).
                        category(data.getCategory()).
                        amount(data.getAmount()).
                        deposit(data.getDeposit()).
                        withdraw(data.getWithdraw()).
                        user(user).build();
                tmpAmount = tmpAmount + data.getWithdraw();
                appAccountHistoryRepository.save(appAccountHistory);
            }

        }catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    // 장고서버로 거래내역 전송후 분석한 데이터 받아옴
//    public Void analysisAppAccountHistory(BankHistoryDTO BankHistory, UUID userId) {
//        String url = "http://127.0.0.1:8000/api/v1/analysis/receive";
//        RestTemplate restTemplate = new RestTemplate();
//
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();
//
//        BankHistoryDTO requestBody = BankHistory;
//        HttpEntity<?> requestEntity = new HttpEntity<>(requestBody, headers);
//        try{
//            ResponseEntity<AppAccountHistoryDto> response = restTemplate.exchange(uri.toString(), HttpMethod.POST, requestEntity, AppAccountHistoryDto.class);
//            AppAccountHistoryDto appAccountHistoryDto = response.getBody();
//            List<AppAccountHistoryDto.DataBody> dataList = appAccountHistoryDto.getData();
//            System.out.println(dataList);
//            Optional<User> optionalUser = userRepository.findById(userId);
//            User user = optionalUser.get();
//            // 정보 저장
//            for(AppAccountHistoryDto.DataBody data : dataList){
//                AppAccountHistory appAccountHistory = AppAccountHistory.builder().
//                        transactionType(data.getTransactionType()).
//                        transactionDate(data.getTransactionDate()).
//                        content(data.getContent()).
//                        category(data.getCategory()).
//                        amount(data.getAmount()).
//                        deposit(data.getDeposit()).
//                        withdraw(data.getWithdraw()).
//                        user(user).build();
//                appAccountHistoryRepository.save(appAccountHistory);
//            }
//        }catch (Exception e) {
//            System.out.println(e.toString());
//        }
//        return null;
//    }
//    public void SaveAppHistory(AppAccountHistoryDto appAccountHistoryDto){
//        AppAccountHistory appAccountHistory = AppAccountHistory.builder().
////                transactionDate(appAccountHistoryDto.getTransactionDate()).
//                content(appAccountHistoryDto).
//                transactionType(appAccountHistoryDto.getTransactionType()).
//                category(appAccountHistoryDto.getCategory()).
//                amount(appAccountHistoryDto.getAmount()).
//                deposit(appAccountHistoryDto.getDeposit()).
//                withdraw(appAccountHistoryDto.getWithdraw()).
//                build();
//        appAccountHistoryRepository.save(appAccountHistory);
//    }
}
