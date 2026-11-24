package com.jingluo.gateway.handler;

import com.jingluo.gateway.constant.Result;
import com.jingluo.gateway.exception.UnauthorizedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalWebExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Result<Void>> handle(UnauthorizedException ex) {
        return Result.error(
                HttpStatus.UNAUTHORIZED,
                ex.authResult.code(),
                ex.authResult.message(),
                ex.authResult.error()
        );
    }

    // 兜底：处理所有未被其他方法精确匹配的异常
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result<Void>> handle(Exception ex) {
        // 生产环境日志记录完整堆栈，但返回给前端的只应是友好提示
        return Result.error(
                HttpStatus.INTERNAL_SERVER_ERROR,
                null,
                "服务器内部错误",
                ex.getMessage()
        );
    }

}
