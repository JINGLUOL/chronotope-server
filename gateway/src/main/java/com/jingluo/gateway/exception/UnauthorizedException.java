package com.jingluo.gateway.exception;

import com.jingluo.gateway.constant.AuthResult;

public class UnauthorizedException extends RuntimeException {

    public final AuthResult authResult;

    public UnauthorizedException(AuthResult result) {
        this.authResult = result;
    }

}
