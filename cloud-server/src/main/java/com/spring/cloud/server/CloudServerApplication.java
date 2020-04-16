package com.spring.cloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients //这个注解是通知SpringBoot在启动的时候，扫描被 @FeignClient 修饰的类
@SpringCloudApplication //组合三个注解的功能，包含注册eureka注册中心，开启熔断，成为spring Boot注解
@EnableHystrixDashboard //开启熔断监控
public class CloudServerApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(CloudServerApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
