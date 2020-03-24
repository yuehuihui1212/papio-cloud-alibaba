package com.papio.contentcenter.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 16:51 2020/2/6
 * @Modified By:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 微信id
     */
    private String wxId;

    /**
     * 微信昵称
     */
    private String wxNickname;

    /**
     * 角色
     */
    private String roles;

    private String avatarUrl;

    private Date createTime;

    private Date updateTime;

    private Integer bonus;
}
