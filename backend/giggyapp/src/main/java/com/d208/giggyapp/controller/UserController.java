package com.d208.giggyapp.controller;

import com.d208.giggyapp.domain.User;
import com.d208.giggyapp.dto.User.KakaoResponseDto;
import com.d208.giggyapp.dto.User.LoginDto;
import com.d208.giggyapp.dto.User.TokenDto;
import com.d208.giggyapp.dto.User.UserDto;
import com.d208.giggyapp.repository.UserRepository;
import com.d208.giggyapp.service.RedisService;
import com.d208.giggyapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;
    private final RedisService redisService;

    private final UserRepository userRepository;

    // 카카오 토큰으로 회원의 이메일을 들고온다.
    // 이메일을 통해 회원 정보를 DB에서 조회한다
    // 회원 O -> 회원 정보 반환
    // 회원 X -> 이메일만 담인 회원 반환
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginDto loginDto) {
        // 레디스에 액세스 토큰 저장
        redisService.setToken(TokenDto.builder().
                accessToken(loginDto.getAccessToken()).
                exist(1).
                build());

        // 카카오 정보 확인
        KakaoResponseDto kakaoResponseDto = userService.getKaKaoInfo(loginDto.getAccessToken());

        // 이메일로 회원 조회
        UserDto userDto = userService.userExist(kakaoResponseDto, loginDto);

        return ResponseEntity.ok(userDto);
    }

    // 넘어온 회원 Dto를 가지고 회원 정보를 저장한다.
    @PostMapping("/register")
    public ResponseEntity<Boolean> signUp(@RequestBody UserDto userDto) {
        User user = User.builder().
                email(userDto.getEmail()).
                nickname(userDto.getNickname()).
                targetAmount(userDto.getTargetAmount()).
                refreshToken(userDto.getRefreshToken()).
                leftLife(userDto.getLeftLife()).
                build();

        userRepository.save(user);

        System.out.println(user.getId());
        return ResponseEntity.ok(true);
    }

    @GetMapping("/{uuid}")
    public User getUUID(@PathVariable UUID uuid) {
        return userRepository.findById(uuid).orElse(null);
    }
}
