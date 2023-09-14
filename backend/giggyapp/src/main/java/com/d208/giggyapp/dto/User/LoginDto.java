package com.d208.giggyapp.dto.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private String accessToken;
    private String refreshToken;
    private String fcmToken;
}
