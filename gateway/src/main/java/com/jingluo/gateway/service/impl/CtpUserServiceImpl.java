package com.jingluo.gateway.service.impl;

import com.jingluo.gateway.client.CtpUserServiceClient;
import com.jingluo.gateway.service.CtpUserService;
import org.springframework.stereotype.Service;

@Service
public class CtpUserServiceImpl implements CtpUserService {

    private final CtpUserServiceClient ctpUserServiceClient;

    public CtpUserServiceImpl(CtpUserServiceClient ctpUserServiceClient) {
        this.ctpUserServiceClient = ctpUserServiceClient;
    }

    @Override
    public Long whetherToBan(String token) {
        return ctpUserServiceClient.whetherToBan(token);
    }

}
