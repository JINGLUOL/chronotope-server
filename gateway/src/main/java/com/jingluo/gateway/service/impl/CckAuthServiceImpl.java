package com.jingluo.gateway.service.impl;

import com.jingluo.gateway.constant.AuthResult;
import com.jingluo.gateway.service.AuthService;
import com.jingluo.gateway.service.CtpUserService;
import org.springframework.stereotype.Service;

@Service
public class CckAuthServiceImpl implements AuthService {

    private final CtpUserService userService;

    public CckAuthServiceImpl(CtpUserService userService) {
        this.userService = userService;
    }

    @Override
    public AuthResult verify(String token) {
        // 令牌判空
        if (token == null || token.isBlank()) {
            return AuthResult.failure(
                    "AUTH_TOKEN_MISSING",
                    "未提供认证凭证",
                    "invalid_request"
            );
        }

        Long userId = userService.whetherToBan(token);
        try {
            if (userId == null) {
                // 令牌失效
                return AuthResult.failure(
                        "AUTH_TOKEN_INVALID",
                        "登录已过期",
                        "invalid_token"
                );
            } else if (userId == -1L) {
                // 账号封禁
                return AuthResult.failure(
                        "AUTH_TOKEN_INVALID",
                        "账号已封禁或注销",
                        "invalid_request"
                );
            } else {
                return AuthResult.success(userId);
            }
        } catch (Exception e) {
            return AuthResult.failure(
                    "AUTH_TOKEN_INVALID",
                    "认证凭证无效",
                    "invalid_token"
            );
        }
    }

}
