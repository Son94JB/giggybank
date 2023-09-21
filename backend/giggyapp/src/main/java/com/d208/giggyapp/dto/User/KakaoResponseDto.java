package com.d208.giggyapp.dto.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class KakaoResponseDto {
    private long id;

    @JsonProperty("connected_at")
    private OffsetDateTime connectedAt;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Data
    public static class KakaoAccount {
        @JsonProperty("email_needs_agreement")
        private boolean emailNeedsAgreement;

        @JsonProperty("is_email_valid")
        private boolean isEmailValid;

        @JsonProperty("is_email_verified")
        private boolean isEmailVerified;

        @JsonProperty("has_email")
        private boolean hasEmail;

        @JsonProperty("email")
        private String email;

        @JsonProperty("birthday")
        private String birthday;
    }
}

