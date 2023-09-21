package com.d208.giggybank.controlloer;

import com.d208.giggybank.domain.BankAccount;
import com.d208.giggybank.dto.*;
import com.d208.giggybank.repository.BankAccountRepository;
import com.d208.giggybank.service.BankService;
import com.d208.giggybank.service.CustomerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
public class BankController {

    private final BankService bankService;
    // 테스트용
    private final CustomerService customerService;

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
    @PostMapping("/bank/add-transaction")
    public ResponseEntity<?> addTransaction(@RequestBody UserTransactionInfoDto userTransactionInfoDto) {
        String accountNumber = userTransactionInfoDto.getAccountNumber();

        bankService.addTransactionService(userTransactionInfoDto);

        return new ResponseEntity<>("저장", HttpStatus.OK);
//        return new ResponseEntity<>("저장실패",HttpStatus.UNAUTHORIZED);
    }


    @PostMapping("/bank/search-transaction")
    public ResponseEntity<List<AccountHistoryDto>> searchTransaction(@RequestBody UserAccountNumberDto userAccountNumberDto) {
        List<AccountHistoryDto> accountHistoryDtos =  bankService.searchTransactionService(userAccountNumberDto);
        System.out.println(accountHistoryDtos);

        // 데이터 없으면
        if(accountHistoryDtos.isEmpty()) {
            return ResponseEntity.notFound().build();
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("계좌 내역이 없습니다.");
        }
        else{
            return ResponseEntity.ok(accountHistoryDtos);
        }

    }

//    private BankAccountRepository bankAccountRepository;
//    @PostMapping("/auth")
//    public ResponseEntity<?> oneAuthResponse(@RequestBody AuthDto authDto) {
//
//        // 클라이언트 서버에서 받은 Dto에서 계좌번호, 생일 가져오기
//        String accountNumber = authDto.getAccountNumber();
//        String birthday = authDto.getBirthday();
//
//        Optional<BankAccount> bankAccount = bankAccountRepository.findByAccountNumber(accountNumber);
//
//        if (bankAccount == null){
//            return
//        }
//
//        // 결과 DTO로 반환
//        AuthDto authDto = AuthDto.builder()
//                .accountNumber(accountNumberToCheck)
//                .birthday(birthday)
//                .build();
//
//        return authDto;
//
//    }

    @PostMapping("/bank/auth")
    public AuthResponseDto checkAuth(@RequestBody AuthDto authDto){
        return bankService.checkAuth(authDto);
    }


    // 테스트용
    @PostMapping("/bank/test")
    public void insertTest(@RequestBody TestDto testDto) {
        customerService.testService(testDto);
    }
}
