package com.d208.giggyapp.service;

import com.d208.giggyapp.domain.User;
import com.d208.giggyapp.dto.SignUpDto;
import com.d208.giggyapp.dto.User.*;
import com.d208.giggyapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.UUIDBinaryType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final RedisService redisService;

    private final UserRepository userRepository;

    // 토큰 만료기간 확인
    public String getTokenExpired(String token) {
        String url = "https://kapi.kakao.com/v1/user/access_token_info";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, Object>> restRequest = new HttpEntity<>(null, headers);

        ResponseEntity<String> apiResponse = restTemplate.exchange(url, HttpMethod.GET, restRequest, String.class);
        String responseBody = apiResponse.getBody();

        return responseBody;
    }

    // 카카오 정보 확인
    public KakaoResponseDto getKaKaoInfo(String token) {
        String url = "https://kapi.kakao.com/v2/user/me";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, Object>> restRequest = new HttpEntity<>(null, headers);

        ResponseEntity<KakaoResponseDto> apiResponse = restTemplate.postForEntity(url, restRequest, KakaoResponseDto.class);
        ResponseEntity<String> apiResponse2 = restTemplate.postForEntity(url, restRequest, String.class);
        KakaoResponseDto responseBody = apiResponse.getBody();

        return responseBody;
    }

    // DB에서 이메일을 조회해서 유저가 있는지 확인
    public UserDto userExist(KakaoResponseDto kakaoResponseDto, LoginDto loginDto) {
        String email = kakaoResponseDto.getKakaoAccount().getEmail();
        String birthday = kakaoResponseDto.getKakaoAccount().getBirthday();
        User user = userRepository.findByEmail(email).orElse(null);

        // 유저가 없는 경우
        if (user == null) {
            return UserDto.builder().
                    email(email).
                    birthday(birthday).
                    build();
        }

        // 유저가 존재하는 경우
        // FCM토큰 갱신
        user.updateFcmToken(loginDto.getFcmToken());
        userRepository.save(user);

        UserDto userDto = UserDto.builder().
                id(user.getId()).
                email(user.getEmail()).
                nickname(user.getNickname()).
                targetAmount(user.getTargetAmount()).
                fcmToken(user.getFcmToken()).
                refreshToken(user.getRefreshToken()).
                leftLife(user.getLeftLife()).build();

        return userDto;
    }


    // 목표소비액 설정
    public ResponseEntity<String> setTargetAmount(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElse(null);

        if (user == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("존재하지 않는 회원입니다.");

        user.updateTargetAmount(userDto.getTargetAmount());
        return ResponseEntity.ok("목표소비액 설정되었습니다.");
    }

    public ResponseEntity<String> incraseLife(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElse(null);

        if (user == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("존재하지 않는 회원입니다.");

        user.incraseLife(2);
        return ResponseEntity.ok("게임 목숨이 증가하였습니다.");
    }

    public ResponseEntity<String> decreaseLife(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElse(null);

        if (user == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("존재하지 않는 회원입니다.");

        if (user.decreaseLife()) {
            return ResponseEntity.ok("게임 목숨이 감소하였습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("남은 목숨이 없습니다.");
        }

    }

    public ResponseEntity<String> initLife(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElse(null);

        if (user == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("존재하지 않는 회원입니다.");

        user.initLife();

        return ResponseEntity.ok("게임 목숨이 초기화되었습니다.");
    }

    public ResponseEntity<String> getLife(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElse(null);

        if (user == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("존재하지 않는 회원입니다.");

        return ResponseEntity.ok(Integer.toString(user.getLeftLife()));
    }

    public ResponseEntity<Boolean> signUp(SignUpDto signUpDto) {
        // 카카오와 통신해서 이메일, 생일을 받아온다.
        KakaoResponseDto kaKaoInfo = this.getKaKaoInfo(signUpDto.getAccessToken());

        User newUser = User.builder().
                email(kaKaoInfo.getKakaoAccount().getEmail()).
                nickname(signUpDto.getNickname()).
                fcmToken(signUpDto.getFcmToken()).
                refreshToken(signUpDto.getRefreshToken()).
                accountNumber(signUpDto.getAccountNumber()).
                birthday(kaKaoInfo.getKakaoAccount().getBirthday()).
                targetAmount(signUpDto.getTargetAmount()).
                leftLife(3).
                build();

        userRepository.save(newUser);
        return ResponseEntity.ok(true);
    }


    public ResponseEntity<Boolean> checkNickname(UserDto userDto) {
        // 닉네임 중복체크
        User user = userRepository.findByNickname(userDto.getNickname()).orElse(null);
        if (user != null) return ResponseEntity.ok(true);

        return ResponseEntity.ok(false);
    }

    public ResponseEntity<UserDto> getUser(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElse(null);

        if (user == null) return ResponseEntity.ok(null);

        return ResponseEntity.ok(UserDto.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .fcmToken(user.getFcmToken())
                .currentAmount(user.getCurrentAmount())
                .refreshToken(user.getRefreshToken())
                .birthday(user.getBirthday())
                .targetAmount(user.getTargetAmount())
                .leftLife(user.getLeftLife()).build());
    }
}

