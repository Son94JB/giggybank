package com.d208.giggyapp.dto.User;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class SendUserDTO {
    private UUID id;
    private String email;
    private String nickname;
    private String fcmToken;
    private String refreshToken;
    private String birthday;
    private int targetAmount;
    private int currentAmount;
    private int leftLife;
    private Long registerDate;
}
