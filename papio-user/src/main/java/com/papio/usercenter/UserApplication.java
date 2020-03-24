package com.papio.usercenter;

import com.papio.usercenter.consumer.CustomizeSink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 17:43 2020/2/4
 * @Modified By:
 **/
@SpringBootApplication
@MapperScan("com.papio.usercenter.mapper")
@EnableDiscoveryClient
@EnableBinding({Sink.class, CustomizeSink.class})
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);
    }
}
