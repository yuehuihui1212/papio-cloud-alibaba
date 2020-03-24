package com.papio.usercenter.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 20:07 2020/2/29
 * @Modified By:
 **/
public interface CustomizeSink {
    String MY_INPUT = "my-input";

    @Input(MY_INPUT)
     SubscribableChannel input();
}
