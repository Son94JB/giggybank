package com.d208.giggyapp.dto.AppAccountHistory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthDTO {
    String Month;
    UUID userId;
}