package com.d208.giggybank.controlloer;

import com.d208.giggybank.dto.AccountHistoryDto;
import com.d208.giggybank.dto.UserAccountNumberDto;
import com.d208.giggybank.dto.UserTransactionInfoDto;
import com.d208.giggybank.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/bank")
public class BankController {

    private final BankService bankService;

//    @PostMapping("/add-transaction")
//    public ResponseEntity<?> addTransaction(@RequestBody UserTransactionInfoDto userTransactionInfoDto) {
//        String accountNumber = userTransactionInfoDto.getAccountNumber();
//
//        bankService.addTransactionService(userTransactionInfoDto);
//
//        return new ResponseEntity<>("저장", HttpStatus.OK);
////        return new ResponseEntity<>("저장실패",HttpStatus.UNAUTHORIZED);
//    }
//
//    @PostMapping("/search-transaction")
//    public ResponseEntity<?> searchTransaction(@RequestBody UserAccountNumberDto userAccountNumberDto) {
//        List<AccountHistoryDto> accountHistoryDtos =  bankService.searchTransactionService(userAccountNumberDto);
//
//        // 데이터 없으면
//        if(accountHistoryDtos.isEmpty()) {
////            return ResponseEntity.notFound().build();
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("계좌 내역이 없습니다.");
//        }
//        else{
//            return ResponseEntity.ok(accountHistoryDtos);
//        }
//
//    }


}
