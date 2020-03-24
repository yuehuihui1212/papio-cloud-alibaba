package com.papio.usercenter.consumer;

import com.papio.usercenter.domain.dto.msg.UserAddBonusMsgDTO;
import com.papio.usercenter.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 12:20 2020/3/1
 * @Modified By:
 **/
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddBonusStreamConsumer {
    private final UserService userService;

    @StreamListener(Sink.INPUT)
    public void recived(UserAddBonusMsgDTO userAddBonusMsgDTO) {
        this.userService.addBonus(userAddBonusMsgDTO);
        log.info("TestStreamConsumer:->{}",userAddBonusMsgDTO);
    }
}
