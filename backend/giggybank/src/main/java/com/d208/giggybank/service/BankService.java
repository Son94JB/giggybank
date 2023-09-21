package com.d208.giggybank.service;

import com.d208.giggybank.domain.BankAccount;
import com.d208.giggybank.domain.BankAccountHistory;
import com.d208.giggybank.dto.AccountHistoryDto;
import com.d208.giggybank.dto.UserAccountNumberDto;
import com.d208.giggybank.dto.UserTransactionInfoDto;
import com.d208.giggybank.repository.BankAccountHistoryRepository;
import com.d208.giggybank.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BankService {

    private final BankAccountRepository bankAccountRepository;
    private final BankAccountHistoryRepository bankAccountHistoryRepository;
//    public BankService(BankAccountRepository bankAccountRepository,BankAccountHistoryRepository bankAccountHistoryRepository) {
//        this.bankAccountRepository = bankAccountRepository;
//        this.bankAccountHistoryRepository = bankAccountHistoryRepository;
//    }

//    @Transactional
//    public void addTransactionService(UserTransactionInfoDto userTransactionInfoDto){
//        String accountnumber = userTransactionInfoDto.getAccountNumber();
//        Integer dwamount = userTransactionInfoDto.getDwamount();
//        String transactionType = userTransactionInfoDto.getTransactionType();
//        String content = userTransactionInfoDto.getContent();
//
//        // 은행 계좌
//        Optional<BankAccount> currentbankaccount = bankAccountRepository.findByAccountNumber(accountnumber);
//
//        System.out.println(currentbankaccount);
//        System.out.println(accountnumber);
//
//        if (currentbankaccount.isPresent() && transactionType.equals("입금")){
//
//            BankAccountHistory bankAccountHistory = BankAccountHistory.builder()
//                    .amount(currentbankaccount.get().getBalance() + dwamount)
//                    .transactionType(transactionType)
//                    .deposit(dwamount)
//                    .content(content)
//                    .transactionDate(LocalDateTime.now())
//                    .bankAccount(currentbankaccount.get())
//                    .build();
//
//            bankAccountHistoryRepository.save(bankAccountHistory);
//
//            currentbankaccount.get().updateBalance(currentbankaccount.get().getBalance() + dwamount);
//            bankAccountRepository.save(currentbankaccount.get());
//
//        } else {
//            BankAccountHistory bankAccountHistory = BankAccountHistory.builder()
//                    .amount(currentbankaccount.get().getBalance() - dwamount)
//                    .transactionType(transactionType)
//                    .withdraw(dwamount)
//                    .content(content)
//                    .transactionDate(LocalDateTime.now())
//                    .bankAccount(currentbankaccount.get())
//                    .build();
//            bankAccountHistoryRepository.save(bankAccountHistory);
//
//            currentbankaccount.get().updateBalance(currentbankaccount.get().getBalance() - dwamount);
//            bankAccountRepository.save(currentbankaccount.get());
//        }
//    }
//
//    @Transactional
//    public List<AccountHistoryDto> searchTransactionService(UserAccountNumberDto userAccountNumberDto){
//        String useraccountnumber = userAccountNumberDto.getAccountNumber();
//        // 사용자 계좌의 객체
//        Optional<BankAccount> currentbankaccount = bankAccountRepository.findByAccountNumber(useraccountnumber);
////        Long useraccountid = currentbankaccount.get().getId();
//        Long useraccountid = currentbankaccount.map(BankAccount::getId)
//                .orElse(null);
//        List<BankAccountHistory> userbankhistories = bankAccountHistoryRepository.findByBankAccountId(useraccountid);
//        //userbankhistory. 데이터들을 dto에 탁탁탁 넣어서  dto를 리턴
//
//
//        System.out.println(useraccountnumber);
//        System.out.println(currentbankaccount);
//        System.out.println(userbankhistories);
//
//
//        List<AccountHistoryDto> accountHistoryDtos = userbankhistories.stream()
//                .map(history -> AccountHistoryDto.builder()
//                        .amount(history.getAmount())
//                        .transactionDate(history.getTransactionDate())
//                        .transactionType(history.getTransactionType())
//                        .deposit(history.getDeposit())
//                        .withdraw(history.getWithdraw())
//                        .content(history.getContent())
//                        .build())
//                .collect(Collectors.toList());
//
//        return accountHistoryDtos;
//    }

}
