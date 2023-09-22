package com.d208.giggybank.service;

import com.d208.giggybank.domain.BankAccount;
import com.d208.giggybank.domain.BankAccountHistory;
import com.d208.giggybank.domain.Customer;
import com.d208.giggybank.dto.*;
import com.d208.giggybank.repository.BankAccountHistoryRepository;
import com.d208.giggybank.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BankService {


    private final BankAccountRepository bankAccountRepository;
    private final BankAccountHistoryRepository bankAccountHistoryRepository;


    @Transactional
    public void addTransactionService(UserTransactionInfoDto userTransactionInfoDto){
        String accountnumber = userTransactionInfoDto.getAccountNumber();
        Integer dwamount = userTransactionInfoDto.getDwamount();
        String transactionType = userTransactionInfoDto.getTransactionType();
        String content = userTransactionInfoDto.getContent();

        // 은행 계좌
        Optional<BankAccount> currentbankaccount = bankAccountRepository.findByAccountNumber(accountnumber);

        if (currentbankaccount.isPresent() && transactionType.equals("입금")){

            BankAccountHistory bankAccountHistory = BankAccountHistory.builder()
                    .amount(currentbankaccount.get().getBalance() + dwamount)
                    .transactionType(transactionType)
                    .deposit(dwamount)
                    .content(content)
                    .transactionDate(LocalDateTime.now())
                    .bankAccount(currentbankaccount.get())
                    .build();

            bankAccountHistoryRepository.save(bankAccountHistory);

            currentbankaccount.get().updateBalance(currentbankaccount.get().getBalance() + dwamount);
            bankAccountRepository.save(currentbankaccount.get());

        } else {
            BankAccountHistory bankAccountHistory = BankAccountHistory.builder()
                    .amount(currentbankaccount.get().getBalance() - dwamount)
                    .transactionType(transactionType)
                    .withdraw(dwamount)
                    .content(content)
                    .transactionDate(LocalDateTime.now())
                    .bankAccount(currentbankaccount.get())
                    .build();
            bankAccountHistoryRepository.save(bankAccountHistory);

            currentbankaccount.get().updateBalance(currentbankaccount.get().getBalance() - dwamount);
            bankAccountRepository.save(currentbankaccount.get());
        }
    }

    @Transactional
    public List<AccountHistoryDto> searchTransactionService(UserAccountNumberDto userAccountNumberDto){
        String useraccountnumber = userAccountNumberDto.getAccountNumber();
        // 사용자 계좌의 객체
        BankAccount currentbankaccount = bankAccountRepository.findByAccountNumber(useraccountnumber).orElse(null);
//        Long bankAccountId = currentbankaccount.get().getId();

//        Long bankAccountId = currentbankaccount.map(BankAccount::getId).orElse(null);

//        List<BankAccountHistory> userbankhistories = bankAccountHistoryRepository.findByBankAccountId(bankAccountId);
        //userbankhistory. 데이터들을 dto에 탁탁탁 넣어서  dto를 리턴

        LocalDateTime startDate = userAccountNumberDto.getStartDate();
        LocalDateTime endDate = userAccountNumberDto.getEndDate();
        List<BankAccountHistory> userbankhistories = bankAccountHistoryRepository.
                findByBankAccountAndTransactionDateTimeBetween(currentbankaccount, startDate, endDate);

        List<AccountHistoryDto> accountHistoryDtos = userbankhistories.stream()
                .map(history -> AccountHistoryDto.builder()
                        .amount(history.getAmount())
                        .transactionDate(history.getTransactionDate())
                        .transactionType(history.getTransactionType())
                        .deposit(history.getDeposit())
                        .withdraw(history.getWithdraw())
                        .content(history.getContent())
                        .build())
                .collect(Collectors.toList());

        return accountHistoryDtos;
    }


    // 계좌번호와 연결된 생일을 조회한 후 받은 값과 일치하면 +1원 후 리턴 아니면 null 리턴
    @Transactional
    public AuthResponseDto checkAuth(AuthDto authDto) {
        String accountNumber = authDto.getAccountNumber();
        String birthday = authDto.getBirthday();

        // 서버의 엔티티에서 해당 계좌번호 검색
//        Optional<BankAccount> bankAccountOptional = bankAccountRepository.findByAccountNumber(accountNumber);
        BankAccount bankAccountOptional = bankAccountRepository.findByAccountNumber(accountNumber).orElse(null);

        System.out.println("aaaaaa = = ="+bankAccountOptional);

        if (bankAccountOptional == null) {
            return AuthResponseDto.builder().build();
        }
        else {
            // 계좌번호 있을 경우
//            int bankAccount = bankAccountOptional.get().getBalance();
//            Customer customer = bankAccountOptional.get().getCustomer();
            int bankAccount = bankAccountOptional.getBalance();
            Customer customer = bankAccountOptional.getCustomer();

            System.out.println(bankAccount);
//            System.out.println("customer는"+customer);
            System.out.println("ID는 " + customer.getId());
            System.out.println(customer.getName());
            System.out.println(customer.getBirthday());



            if (customer != null && customer.getBirthday().equals(birthday)) {
                // 일치하는 생일 있을 경우

                // 1원 이체(=1원추가)
//                int updatedAmount = bankAccountOptional.getBalance() + 1;
//                bankAccountOptional.updateBalance(updatedAmount);

                return AuthResponseDto.builder()
                        .amount(1)
                        .withdraw(1)
                        .deposit(0)
                        .transactionDate(LocalDateTime.now())
                        .transactionType("입금")
                        .content(generateRandomContent())
                        .build();
            }
            else {
                // 빈 AuthResponseDto 반환
                return AuthResponseDto.builder().build();
            }


        }

    }

    private String generateRandomContent() {
        // 랜덤 문자열 (4글자)
        Random random = new Random();
        StringBuilder randomWord = new StringBuilder();
//        StringBuilder contentBuilder = new StringBuilder()

        for(int i=0; i<4; i++){
            char randomChar = (char) (0xAC00 + random.nextInt(11172));
            // 0xAC00은 '가'의 유니코드 코드 포인트, 11172는 한글 음절의 개수
            randomWord.append(randomChar);
        }
        return randomWord.toString();
    }

//    private char generateRandomChar() {
//        Random random = new Random();
//
//        // 한국어 유니코드 (가 - 힣)
//        int start = 0xAC00;
//        int end = 0xD7A3;
//
//        int randomCode = start + random.nextInt(end-start+1);
//
//        return (char) randomCode;
//    }

}
