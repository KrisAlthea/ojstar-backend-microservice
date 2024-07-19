package com.ryanverse.ojstarbackendquestionservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 主类（项目启动入口）
 *
 * @author Haoran
 */

@SpringBootApplication
//@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
@MapperScan("com.ryanverse.ojstarbackendquestionservice.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.ryanverse")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.ryanverse.ojstarbackendserviceclient.service"})
public class OjstarBackendQuestionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OjstarBackendQuestionServiceApplication.class, args);
    }

}
