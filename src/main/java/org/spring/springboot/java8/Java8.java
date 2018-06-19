package org.spring.springboot.java8;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by nice on 2018/6/6.
 */
@Slf4j
@Service
public class Java8 {

    @Scheduled(cron = "0 27 17 * * *")
    public void testSwitch() {
        log.info("当前时间 " + LocalDateTime.now());
    }
}
