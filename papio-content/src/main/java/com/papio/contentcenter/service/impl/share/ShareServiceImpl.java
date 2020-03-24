package com.papio.contentcenter.service.impl.share;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import com.papio.contentcenter.domain.dto.msg.UserAddBonusMsgDTO;
import com.papio.contentcenter.domain.dto.share.ShareAuditDTO;
import com.papio.contentcenter.domain.dto.share.ShareDTO;
import com.papio.contentcenter.domain.dto.user.UserDTO;
import com.papio.contentcenter.domain.entity.content.RocketmqTransactionLog;
import com.papio.contentcenter.domain.entity.content.Share;
import com.papio.contentcenter.domain.enums.AuditStatusEnum;
import com.papio.contentcenter.feignclient.UserFeignClient;
import com.papio.contentcenter.mapper.content.RocketmqTransactionLogMapper;
import com.papio.contentcenter.mapper.content.ShareMapper;
import com.papio.contentcenter.service.share.ShareService;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 17:01 2020/2/6
 * @Modified By:
 **/
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ShareServiceImpl implements ShareService {
    private final ShareMapper shareMapper;
    private final  UserFeignClient userFeignClient;
    private final  RocketMQTemplate rocketMQTemplate;
    private final  RocketmqTransactionLogMapper rocketmqTransactionLogMapper;
    private final Source source;

    @Override
    @SentinelResource("common")
    public ShareDTO queryShareById(Integer id) {
        Share share = shareMapper.selectByPrimaryKey(id);
        UserDTO user = userFeignClient.findUserById(share.getUserId());
        ShareDTO shareDTO = ShareDTO.builder().build();
        BeanUtils.copyProperties(share,shareDTO);
        shareDTO.setWxNickname(user.getWxNickname());
        return shareDTO;
    }

    @Override
    public UserDTO queryUser(UserDTO userDTO) {
        return userFeignClient.query(userDTO);
    }

    @Override
    public ResponseEntity auditById(Integer id, ShareAuditDTO shareAuditDTO) {
        Share share = shareMapper.selectByPrimaryKey(id);
        if (share == null) {
            throw new IllegalArgumentException("参数非法");
        }
        if (!Objects.equals(AuditStatusEnum.NOT_YET.name(), share.getAuditStatus())) {
            throw new IllegalArgumentException("参数非法");
        }
        if (AuditStatusEnum.PASS.equals(shareAuditDTO.getAuditStatusEnum())) {
            //springcloudstream 整合 rocketMQ来实现分布式事务
            this.source.output().send(MessageBuilder.withPayload( UserAddBonusMsgDTO.builder()
                    .userId(share.getUserId())
                    .bonus(500)
                    .build())
                    .setHeader(RocketMQHeaders.TRANSACTION_ID, UUID.randomUUID().toString())
                    .setHeader("share_id",id)
                    .setHeader("dto", JSON.toJSONString(shareAuditDTO))
                    .build());
            //单独用rocketmq实现分布式事务
            /*rocketMQTemplate.sendMessageInTransaction("tx-add-bonus","add-bonus",
                    MessageBuilder.withPayload( UserAddBonusMsgDTO.builder()
                            .userId(share.getUserId())
                            .bonus(500)
                            .build())
                    .setHeader("transaction_id", UUID.randomUUID().toString())
                    .setHeader("share__id",id)
                    .build(),
                            shareAuditDTO);*/
        }else{
            auditShareById(id, shareAuditDTO);
        }
        int a=1/0;
        return ResponseEntity.ok(share);
    }


    @Transactional(rollbackFor = Exception.class)
    public void auditShareById(Integer id, ShareAuditDTO shareAuditDTO) {
        Share share = Share.builder()
                .id(id)
                .auditStatus(shareAuditDTO.getAuditStatusEnum().toString())
                .reason(shareAuditDTO.getReason())
                .build();
        shareMapper.updateByPrimaryKeySelective(share);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void auditShareByIdWithMqTxLog(Integer id, ShareAuditDTO shareAuditDTO, String transactionId) {
        this.auditShareById(id, shareAuditDTO);
        this.rocketmqTransactionLogMapper.insertSelective(
                RocketmqTransactionLog.builder()
                .transactionId(transactionId)
                .log("分享成功")
                .build()
        );
    }
}
