package com.jingluo.gateway.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "security.whitelist")
@Data
public class WhitelistProperties {

    private List<String> paths = new ArrayList<>();

}
