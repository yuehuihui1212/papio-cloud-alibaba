package com.papio.contentcenter.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 15:36 2020/2/9
 * @Modified By:
 **/
public class UserCenterFeignConfig {

    @Bean
    public Logger.Level level(){
        return Logger.Level.FULL;
    }
}
