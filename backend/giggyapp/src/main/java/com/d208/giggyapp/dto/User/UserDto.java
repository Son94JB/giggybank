package com.d208.giggyapp.dto.User;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class UserDto {
    private UUID id;
    private String email;
    private String nickname;
    private String fcmToken;
    private String refreshToken;
    private int targetAmount;
    private int leftLife;
}