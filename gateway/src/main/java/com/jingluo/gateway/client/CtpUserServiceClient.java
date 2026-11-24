package com.jingluo.gateway.client;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/user")
public interface CtpUserServiceClient {

    @PostExchange("/ban-status")
    Long whetherToBan(@RequestBody String token);

}
