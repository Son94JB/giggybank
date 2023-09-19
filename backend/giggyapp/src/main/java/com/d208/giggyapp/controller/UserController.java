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
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.Map;
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
        // 카카오 정보 확인
        KakaoResponseDto kakaoResponseDto = userService.getKaKaoInfo(loginDto.getAccessToken());

        // 이메일로 회원 조회
        UserDto userDto = userService.userExist(kakaoResponseDto, loginDto);

        // 빈 유저가 아닌 경우에만 액세스 토큰을 레디스에 보관
        if (userDto.getRefreshToken() != null) {
            redisService.setAccessToken(TokenDto.builder().
                    accessToken(loginDto.getAccessToken()).
                    exist(1).
                    build());
        }

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

    // 테스트용 컨트롤러
    @PostMapping("/hello")
    public String test(@RequestHeader HttpHeaders header) {
        String accessToken = header.getFirst("Authorization");
        return accessToken;
    }

    // 토큰이 만료됨을 보낸다.
    @PostMapping("/expired")
    public ResponseEntity<String> expired() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰이 만료되었습니다");
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refresh(@RequestHeader HttpHeaders header) {
        String refreshToken = header.getFirst("Authorization");
        redisService.issueAccessToken(refreshToken);

        return ResponseEntity.ok("gg");
    }
}
