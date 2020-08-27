package com.spring.cloud.client;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.spring.cloud.client.dao")
@EnableDiscoveryClient
public class CloudClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudClientApplication.class, args);
    }

}
