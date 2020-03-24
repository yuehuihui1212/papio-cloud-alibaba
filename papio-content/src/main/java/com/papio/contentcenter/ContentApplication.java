package com.papio.contentcenter;

import com.papio.contentcenter.config.rocketmq.CustomizeSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 13:37 2020/2/6
 * @Modified By:
 **/
@SpringBootApplication
@MapperScan("com.papio.contentcenter.mapper")
@EnableDiscoveryClient
@EnableFeignClients
@EnableBinding({Source.class, CustomizeSource.class})
public class ContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class);
    }
}
