package com.jingluo.serviceuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.service.registry.ImportHttpServices;

@SpringBootApplication
@ImportHttpServices(
        group = "reg",                    // 分组名称
        basePackages = "com.jingluo.user" // 扫描该包下所有 @HttpExchange 接口
)
@MapperScan("com.jingluo.serviceuser.mapper")
public class ServiceUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class, args);
    }

}
