package com.htht.security.browser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(value="com.htht.*")
public class SecurityWebsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityWebsApplication.class, args);
    }

}
