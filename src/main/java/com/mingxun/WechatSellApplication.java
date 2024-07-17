package com.mingxun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@MapperScan(basePackages = "com.mingxun.dataobject.mapper")
public class WechatSellApplication {

	public static void main(String[] args) {
		SpringApplication.run(WechatSellApplication.class, args);
	}

}
