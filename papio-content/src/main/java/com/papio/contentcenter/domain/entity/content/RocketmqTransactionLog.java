package com.papio.contentcenter.domain.entity.content;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "rocketmq_transaction_log")
public class RocketmqTransactionLog {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "transaction_id")
    private String transactionId;

    private String log;
}