package com.jingluo.serviceuser.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "gateway.auth")
public record AuthProperties(String[] publicKey, long validDuration) {
}
