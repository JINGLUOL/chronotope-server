package com.jingluo.dto.resp;

public record ResultSuc<T>(String code, String msg, T data) {
}
