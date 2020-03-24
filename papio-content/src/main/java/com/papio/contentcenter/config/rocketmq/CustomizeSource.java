package com.papio.contentcenter.config.rocketmq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @Author: yuehuihui
 * @Description:自定义SpringCloudStream  Source
 * @Date: created in 19:57 2020/2/29
 * @Modified By:
 **/
public interface CustomizeSource {
      String MY_OUTPUT = "my-output";
      @Output(MY_OUTPUT)
      MessageChannel output();
}
