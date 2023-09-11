package com.d208.giggyapp.controller;

import com.d208.giggyapp.dto.SignUpDto;
import com.d208.giggyapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody SignUpDto signUpDto) {
        return userService.getKaKaoInfo(signUpDto.getToken());
    }
}
