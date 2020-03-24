package com.papio.usercenter.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 19:41 2020/2/29
 * @Modified By:
 **/
@Service
@Slf4j
public class TestStreamConsumer {
    @StreamListener(Sink.INPUT)
    public void recived(String message) {
        log.info("TestStreamConsumer:->{}",message);
    }

    @StreamListener(CustomizeSink.MY_INPUT)
    public void customizeRecived(String message) {
        log.info("customizeStreamConsumer:->{}",message);
    }
}
