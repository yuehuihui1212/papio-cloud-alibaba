package com.papio.common.enums;

import lombok.*;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 13:46 2018/12/30
 * @Modified By:
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {
    TOKEN_NOT_VALID(401,"token不合法");
    private int code;
    private String msg;
}
