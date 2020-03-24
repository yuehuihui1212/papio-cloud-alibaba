package com.papio.contentcenter.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 18:56 2020/2/12
 * @Modified By:
 **/
@Getter
@AllArgsConstructor
public enum AuditStatusEnum {
    /**
     * 待审核
     */
    NOT_YET,
    /**
     * 审核通过
     */
    PASS,
    /**
     * 审核不通过
     */
    REJECT
}
