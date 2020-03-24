package com.papio.contentcenter.domain.dto.msg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 20:38 2020/2/12
 * @Modified By:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAddBonusMsgDTO {
    /**
     * 为谁增加积分
     */
    private Integer userId;
    /***
     * 增加积分数
     */
    private Integer bonus;
}
