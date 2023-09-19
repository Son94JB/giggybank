package com.d208.giggyapp.service;

import com.d208.giggyapp.domain.User;
import com.d208.giggyapp.dto.User.*;
import com.d208.giggyapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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
        KakaoResponseDto responseBody = apiResponse.getBody();

        return responseBody;
    }

    // DB에서 이메일을 조회해서 유저가 있는지 확인
    public UserDto userExist(KakaoResponseDto kakaoResponseDto, LoginDto loginDto) {
        String email = kakaoResponseDto.getKakaoAccount().getEmail();

        User user = userRepository.findByEmail(email).orElse(null);

        // 유저가 없는 경우
        if (user == null) {
            return UserDto.builder().
                    email(email).
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

    // 리프레시 토큰을 조회해서 새로 액세스 토큰 발급하기
    public String issueAccessToken(String refreshToken) {

        // 리프레시 토큰으로 카카오 서버와 연동해서 accessToken 발급받기
        String url = "https://kauth.kakao.com/oauth/token";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded;");

        // 요청을 보낼 데이터 생성
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "refresh_token");
        requestBody.add("client_id", "3d8e6aac6079ec287090de67e9d97584"); // 환경 변수로 빼야함
        requestBody.add("refresh_token", refreshToken);

        // 요청 엔티티 생성
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<KakaoRefreshResponseDto> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, KakaoRefreshResponseDto.class);
        KakaoRefreshResponseDto kakaoRefreshResponseDto = response.getBody();
        String accessToken = kakaoRefreshResponseDto.getAccessToken();

        // 레디스에 토큰 저장
        redisService.setAccessToken(TokenDto.builder()
                .accessToken(accessToken)
                .exist(1)
                .build());


        // 클라이언트로 내려주기
        return accessToken;
    }
}

