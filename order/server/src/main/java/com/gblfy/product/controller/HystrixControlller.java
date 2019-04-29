package com.gblfy.product.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @ClassName HystrixControlller
 * @Deacription 服务降级
 * @Author gblfy
 * @Date 2019/4/28 13:11
 * @Version 1.0
 **/
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixControlller {


    /*********************************测试服务降级***************************************/

    /*
        超时设置 应用场景：根据调用方而制定的，
        比如：调用方时常需要3s，但是springcloud不设置超时时间，
            默认超时设置1s，这样，调用服务还没完成调用服务，就返回服
           务降级了，因此，需要需要设置超时时间
     */
    //设置超时时间3s(不采用默认超时时间1s)
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//    })
//    @GetMapping("/getProductInfoList")
//    public String getProductInfoList() {
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.postForObject("http://127.0.0.1:8082/product/listForOrder",
//                Arrays.asList("157875196366160022"), String.class);
//    }

    /*********************************测试服务降级***************************************/

    /*********************************测试熔断机制***************************************/
    //服务熔断
    /*
        测试熔断：
        1.添加传递参数number
        2.请求偶数返回success，不会触发熔断
        3.当传递的number不是偶数，就会调用product商品服务，
          这样就会触发熔断机制，因为默认超时时间为1s，而在product
          商品服务调用/product/getProductInfoList接口服务设置睡眠2s，因此会触发熔断机制
     */
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//设置熔断
//            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求数达到后才计算
//            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//熔断时间
//            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")//错误率
//    })
    @HystrixCommand
    @GetMapping("/getProductInfoList")
    public String getProductInfoList(@RequestParam("number") Integer number) {
        if (number % 2 == 0) {
            return "success";
        }
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://127.0.0.1:8082/product/listForOrder",
                Arrays.asList("157875196366160022"), String.class);
    }
/*
    测试链接：
    http://127.0.0.1:8081/getProductInfoList?number=1
    返回数据：默认提示：太拥挤了,请稍后再试~

    http://127.0.0.1:8081/getProductInfoList?number=2
    返回数据：success
 */
    /*********************************测试熔断机制***************************************/
    private String fallback() {
        return "太拥挤了,请稍后再试~";
    }

    private String defaultFallback() {
        return "默认提示：太拥挤了,请稍后再试~";
    }
}
