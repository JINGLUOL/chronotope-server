package com.jingluo.serviceuser.config;

import com.jingluo.serviceuser.constant.AuthProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
@EnableConfigurationProperties(AuthProperties.class)
public class TimeConfig {

    @Bean
    public Clock clock() {
        // 通常推荐使用 UTC 时区，以避免服务器环境时区不一致带来的问题
        return Clock.systemUTC();
        // 或者使用系统默认时区：return Clock.systemDefaultZone();
    }

}
