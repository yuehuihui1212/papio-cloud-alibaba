package com.papio.usercenter.domain.entity.user;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "bonus_event_log")
public class BonusEventLog {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 积分值
     */
    private Integer value;

    private String event;

    @Column(name = "create__time")
    private Date createTime;

    private String description;
}