package com.jingluo.dto.resp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class Result {

    public static ResponseEntity<ResultError> error(
            HttpStatus status, String code, String msg, String error
    ) {
        return ResponseEntity.status(status).body(
                new ResultError(code, msg, error)
        );
    }

    public static ResponseEntity<ResultSuc<?>> ok(
            HttpStatus status, String code, String msg, Object data
    ) {
        return ResponseEntity.status(status).body(
                new ResultSuc<>(code, msg, data)
        );
    }

}
