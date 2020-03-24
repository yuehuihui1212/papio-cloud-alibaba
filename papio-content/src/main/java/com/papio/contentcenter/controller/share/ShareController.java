package com.papio.contentcenter.controller.share;

import com.papio.contentcenter.config.rocketmq.CustomizeSource;
import com.papio.contentcenter.domain.dto.share.ShareDTO;
import com.papio.contentcenter.domain.dto.user.UserDTO;
import com.papio.contentcenter.service.share.ShareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 17:06 2020/2/6
 * @Modified By:
 **/
@RestController
@RequestMapping("share")
@Slf4j
public class ShareController {
    @Autowired
    private ShareService shareService;

    @GetMapping("queryShareById/{id}")
    public ResponseEntity queryShareById(@PathVariable Integer id) {
        ShareDTO shareDTO = shareService.queryShareById(id);
        return ResponseEntity.ok(shareDTO);
    }

    @GetMapping("queryUser")
    public ResponseEntity queryUser(UserDTO userDTO) {
        UserDTO userDTO1 = shareService.queryUser(userDTO);
        return ResponseEntity.ok(userDTO1);
    }
    @GetMapping("queryShareById1/{id}")
    public ResponseEntity queryShareById1(@PathVariable Integer id) {
        ShareDTO shareDTO = shareService.queryShareById(id);
        return ResponseEntity.ok(shareDTO);
    }

    @Autowired
    private Source source;
    @GetMapping("test-stream")
    public ResponseEntity testStream() {
        this.source.output()
                .send(MessageBuilder.withPayload("stream test").build());
        return ResponseEntity.ok().build();
    }
    @Autowired
    private CustomizeSource customizeSource;
    @GetMapping("test-my-stream")
    public ResponseEntity testCustomizeSourceStream() {
        this.customizeSource.output()
                .send(MessageBuilder.withPayload("customizeSource stream source test").build());
        return ResponseEntity.ok().build();
    }
}
