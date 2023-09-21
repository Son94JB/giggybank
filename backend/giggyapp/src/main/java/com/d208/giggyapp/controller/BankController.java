package com.d208.giggyapp.controller;

import com.d208.giggyapp.domain.User;
import com.d208.giggyapp.dto.Auth1Dto;
import com.d208.giggyapp.repository.UserRepository;
import com.d208.giggyapp.service.BankService;
import com.d208.giggyapp.service.FirebaseCloudMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BankController {
    private final BankService bankService;
    private final FirebaseCloudMessageService firebaseCloudMessageService;

    private final UserRepository userRepository;
    // 1원 인증
    @PostMapping("/bank/auth")
    public void oneAuth(@RequestBody Auth1Dto auth1Dto) throws IOException {
        // 은행에 1원 인증 요청해서 응답을 받는다.
        bankService.oneAuthRequest(auth1Dto);

        // FCM
        // 서비스로 분리해야함.
        String token = "";
        String title = "1원 송금";
        String body = "나 무 늘 보";
        firebaseCloudMessageService.sendMessageTo(token, title, body);

    }
}
