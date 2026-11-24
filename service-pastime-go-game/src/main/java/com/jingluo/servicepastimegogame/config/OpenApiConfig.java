package com.jingluo.servicepastimegogame.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("围棋服务 API")
                .version("1.0")
                .description("service-pastime-go-game 模块接口文档")
        );
    }

}
