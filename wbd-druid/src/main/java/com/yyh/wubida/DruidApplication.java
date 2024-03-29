package com.yyh.wubida;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DruidApplication {
	public static void main(String[] args) {
		//Sentry.init("https://697037ec392b4c818710b0892971a1fa@sentry.itheima.net/23");
		SpringApplication.run(DruidApplication.class, args);
	}
}
