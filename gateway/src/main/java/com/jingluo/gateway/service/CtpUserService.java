package com.jingluo.gateway.service;

public interface CtpUserService {

    /**
     * 获取用户密码验证信息
     *
     * @param token 用户ID
     * @return 用户加密密码
     */
    Long whetherToBan(String token);

}
