package com.spring.cloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients //这个注解是通知SpringBoot在启动的时候，扫描被 @FeignClient 修饰的类
@EnableDiscoveryClient
@SpringCloudApplication
public class CloudServerApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(CloudServerApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
