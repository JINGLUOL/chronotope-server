package com.jingluo.gateway.constant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public record Result<T>(String code, String msg, T data, String error) {

    public static ResponseEntity<Result<Void>> error(
            HttpStatus status, String code, String msg, String error
    ) {
        return ResponseEntity.status(status).body(new Result<>(
                code, msg, null, error
        ));
    }

    public static ResponseEntity<Result<?>> error(
            HttpStatus status, Result<?> result
    ) {
        return ResponseEntity.status(status).body(result);
    }

}
