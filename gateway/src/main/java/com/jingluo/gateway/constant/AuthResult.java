package com.jingluo.gateway.constant;

/**
 * @param code    业务错误码，如 AUTH_TOKEN_EXPIRED
 * @param message 用户可读的错误信息
 * @param error   WWW-Authenticate 里的 error 值，如 invalid_token
 */
public record AuthResult(boolean authenticated, Long userId, String code, String message, String error) {

    public static AuthResult success(Long userId) {
        return new AuthResult(true, userId, null, null, null);
    }

    public static AuthResult failure(String code, String message, String error) {
        return new AuthResult(false, null, code, message, error);
    }

}
