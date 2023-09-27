package com.d208.giggyapp.service;

import com.d208.giggyapp.domain.AppAccountHistory;
import com.d208.giggyapp.domain.User;
import com.d208.giggyapp.dto.appAccountHistory.*;
import com.d208.giggyapp.repository.AppAccountHistoryRepository;
import com.d208.giggyapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AppAccountHistoryService {
    private final AppAccountHistoryRepository appAccountHistoryRepository;
    private final UserRepository userRepository;

    // 은행으로부터 거래내역 받아오기
    public Void getBankAccountHistory(UUID userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        String accountNumber = user.getAccountNumber();
        LocalDateTime startDate;
        LocalDateTime endDate;

        // 거래내역이 있는지 확인
        Optional<AppAccountHistory> optionalAppAccountHistory = appAccountHistoryRepository.findFirstByUserOrderByTransactionDateDesc(user);
        if(optionalAppAccountHistory.isPresent()){
            AppAccountHistory appAccountHistory = optionalAppAccountHistory.get();
            System.out.println(appAccountHistory.getId());
            startDate = appAccountHistory.getTransactionDate();
            endDate = LocalDateTime.now();
        }else{
            startDate = LocalDateTime.now().withDayOfMonth(1);
            endDate = LocalDateTime.now();
        }
        System.out.println(startDate);
        System.out.println(endDate);


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
            ResponseEntity<AppAccountHistoryDTO> response = restTemplate.exchange(uri.toString(), HttpMethod.POST, requestEntity, AppAccountHistoryDTO.class);
            AppAccountHistoryDTO appAccountHistoryDto = response.getBody();
            List<AppAccountHistoryDTO.DataBody> dataList = appAccountHistoryDto.getData();
            System.out.println(dataList);

            // 정보 저장
            int tmpAmount = 0;
            for(AppAccountHistoryDTO.DataBody data : dataList) {
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
            user.incraseCurrentAmount(tmpAmount);
        }catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    // 분석내역 조회
    public List<AnalysisDTO> getAnalysis(MonthDTO monthDTO){
        Optional<User> optionalUser = userRepository.findById(monthDTO.getUserId());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            YearMonth yearMonth = YearMonth.parse(monthDTO.getMonth());
            LocalDateTime startDate = yearMonth.atDay(1).atStartOfDay();
            LocalDateTime endDate = yearMonth.atEndOfMonth().atTime(23,59,59);
            List<AppAccountHistory> appAccountHistories = appAccountHistoryRepository.findByUserAndTransactionDateTimeBetween(user, startDate, endDate);
            // 항상 6가지 카테고리를 미리 정의
            List<String> predefinedCategories = Arrays.asList("교통", "식품", "기타", "고정지출", "쇼핑", "자기계발","여가");

            // 카테고리별 지출 합계를 저장할 맵 초기화
            Map<String, BigDecimal> categorySumMap = new HashMap<>();
            for (String category : predefinedCategories) {
                categorySumMap.put(category, BigDecimal.ZERO);
            }

            for (AppAccountHistory appAccountHistory : appAccountHistories) {
                String category = appAccountHistory.getCategory();
                int withdraw = appAccountHistory.getWithdraw();
                BigDecimal bigwithdraw = new BigDecimal(withdraw);

                if (predefinedCategories.contains(category)) {
                    BigDecimal currentSum = categorySumMap.get(category);
                    categorySumMap.put(category, currentSum.add(bigwithdraw));
                }
            }

            List<AnalysisDTO> analysisDTOS = new ArrayList<>();
            for (String key : predefinedCategories) {
                BigDecimal value = categorySumMap.get(key);
                BigDecimal roundedValue = value.setScale(0, RoundingMode.HALF_UP); // 반올림
                Integer integerValue = roundedValue.intValue();
                AnalysisDTO analysisDTO = AnalysisDTO.builder()
                        .categoryName(key)
                        .price(integerValue)
                        .build();
                analysisDTOS.add(analysisDTO);
            }

            return analysisDTOS;
        }
        return null;
    }

    // 카테고리 업데이트
    public Void updateCategory(Long id, String category){
        Optional<AppAccountHistory> optionalAppAccountHistory = appAccountHistoryRepository.findById(id);
        if(optionalAppAccountHistory.isPresent()){
            AppAccountHistory appAccountHistory = optionalAppAccountHistory.get();
            appAccountHistory.updateCategory(category);
        }else {
            System.out.println("존재하지 않는 거래내역입니다.");
        }
        return null;
    }

    // 페이지네이션을 위한 달 조회
    public List<String> getMonth(UUID userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            LocalDateTime registerDate = user.getRegisterDate();
            LocalDateTime currentDate = LocalDateTime.now();
            List<String> yearMonths = new ArrayList<>();

            while (!registerDate.isAfter(currentDate)){
                YearMonth yearMonth = YearMonth.from(registerDate);
                yearMonths.add(yearMonth.toString());
                registerDate = registerDate.plusMonths(1);
            }
            return yearMonths;
        }else {
            return null;
        }
    }

    // 거래내역 조회
    public List<AccountHistoryDTO> getAppAccountHistory(@RequestBody DateDTO accountHistoryDateDTO){
        System.out.println(accountHistoryDateDTO);
        String startDate = accountHistoryDateDTO.getStartDate() + " 00:00:00";
        String endDate = accountHistoryDateDTO.getEndDate() + " 23:59:59";
//        String startDate = "2023-07-22" + " 00:00:00";
//        String endDate = "2023-09-22" + " 23:59:59";
        UUID userId = accountHistoryDateDTO.getUserId();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse(startDate, formatter);
        LocalDateTime end = LocalDateTime.parse(endDate, formatter);
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            List<AppAccountHistory> appAccountHistories = appAccountHistoryRepository.findByUserAndTransactionDateTimeBetween(user, start, end);
            List<AccountHistoryDTO> appAccountHistoryDTOs = appAccountHistories.stream()
                    .map(history -> AccountHistoryDTO.builder().
                            amount(history.getAmount()).
                            transactionType(history.getTransactionType()).
                            transactionDate(history.getTransactionDate().atZone(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli()).
                            deposit(history.getDeposit()).
                            withdraw(history.getWithdraw()).
                            deposit(history.getDeposit()).
                            content(history.getContent()).
                            id(history.getId()).
                            category(history.getCategory()).build())
                    .collect(Collectors.toList());
            System.out.println(appAccountHistoryDTOs);
            return appAccountHistoryDTOs;
        }else {
            System.out.println("존재하지않는 유저입니다.");
            return null;
        }
    }

}
