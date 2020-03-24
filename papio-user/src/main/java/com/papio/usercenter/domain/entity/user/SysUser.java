package com.papio.usercenter.domain.entity.user;

import java.util.Date;
import javax.persistence.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "sys_user")
public class SysUser {
    /**
     * 用户id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 微信id
     */
    @Column(name = "wx_id")
    private String wxId;

    /**
     * 微信昵称
     */
    @Column(name = "wx_nickname")
    private String wxNickname;

    /**
     * 角色
     */
    private String roles;

    @Column(name = "avatar__url")
    private String avatarUrl;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update__time")
    private Date updateTime;

    private Integer bonus;
}