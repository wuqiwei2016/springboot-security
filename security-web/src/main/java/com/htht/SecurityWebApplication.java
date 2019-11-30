package com.htht;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.htht.mapper")
public class SecurityWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityWebApplication.class, args);
	}

}
