package com.papio.contentcenter.config.rocketmq;

import com.alibaba.fastjson.JSON;
import com.papio.contentcenter.domain.dto.share.ShareAuditDTO;
import com.papio.contentcenter.domain.entity.content.RocketmqTransactionLog;
import com.papio.contentcenter.mapper.content.RocketmqTransactionLogMapper;
import com.papio.contentcenter.service.share.ShareService;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 19:32 2020/2/28
 * @Modified By:
 **/
@RocketMQTransactionListener(txProducerGroup = "tx-add-bonus")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddBonusTransactionListener implements RocketMQLocalTransactionListener {
    private final ShareService shareService;
    private final RocketmqTransactionLogMapper rocketmqTransactionLogMapper;
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        MessageHeaders headers = message.getHeaders();
        String transactionId= (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        Integer id =Integer.valueOf(headers.get("share_id").toString());
        String dto= (String) headers.get("dto");
        ShareAuditDTO shareAuditDTO = JSON.parseObject(dto, ShareAuditDTO.class);
        try {
            this.shareService.auditShareByIdWithMqTxLog(id,shareAuditDTO,transactionId);
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        MessageHeaders headers = message.getHeaders();
        String transactionId= (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        RocketmqTransactionLog rocketmqTransactionLog = rocketmqTransactionLogMapper.selectOne(
                RocketmqTransactionLog.builder().transactionId(transactionId).build()
        );
        if (rocketmqTransactionLog != null) {
            return RocketMQLocalTransactionState.COMMIT;
        }
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}
