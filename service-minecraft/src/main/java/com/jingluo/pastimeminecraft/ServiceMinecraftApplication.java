package com.jingluo.pastimeminecraft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jingluo.pastimeminecraft.mapper")
public class ServiceMinecraftApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceMinecraftApplication.class, args);
	}

}
