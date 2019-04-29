package com.gblfy.product;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker
@EnableFeignClients
@SpringCloudApplication
@EnableHystrixDashboard
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
