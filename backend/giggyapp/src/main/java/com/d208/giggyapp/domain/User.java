package com.d208.giggyapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String email;

    @Column(columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String nickname;

    private String fcmToken;

    private String refreshToken;

    private String accountNumber;

    private String birthday;

    private int targetAmount;

    private int currentAmount;

    private int leftLife;

    private LocalDateTime registerDate;

    public void updateFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public void updateTargetAmount(int targetAmount) {
        this.targetAmount = targetAmount;
    }

    public void incraseLife(int life) {
        this.leftLife += life;
    }

    public Boolean decreaseLife() {
        if (this.leftLife - 1 >= 0) {
            this.leftLife -= 1;
            return true;
        } else {
            return false;
        }
    }

    public void initLife() {
        this.leftLife = 3;
    }

    public void incraseCurrentAmount(int amount) {
        this.currentAmount += amount;
    }
}
