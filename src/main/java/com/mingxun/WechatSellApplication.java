package com.mingxun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class WechatSellApplication {

	public static void main(String[] args) {
		SpringApplication.run(WechatSellApplication.class, args);
	}

}
