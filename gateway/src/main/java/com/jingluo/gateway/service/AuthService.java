package com.jingluo.gateway.service;

import com.jingluo.gateway.constant.AuthResult;

public interface AuthService {

    /**
     * 校验 token，永不抛异常，失败通过 AuthResult 返回
     *
     * @param token 可能为 null（没带 Authorization 头）
     */
    AuthResult verify(String token);

}
