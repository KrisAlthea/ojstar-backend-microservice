package com.ryanverse.ojstarbackendjudgeservice;

import com.ryanverse.ojstarbackendjudgeservice.rabbitmq.InitRabbitMQ;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.ryanverse")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.ryanverse.ojstarbackendserviceclient.service"})
public class OjstarBackendJudgeServiceApplication {

    public static void main(String[] args) {
        InitRabbitMQ.doInit();
        SpringApplication.run(OjstarBackendJudgeServiceApplication.class, args);
    }

}
