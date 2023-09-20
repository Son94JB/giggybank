package com.d208.giggyrank.config;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AutoRounder {

    @Scheduled(cron = "0 0 0 * * 1", zone = "Asia/Seoul")
    public void AutoRoundAdd() {

    }

}
