package com.papio.common.vo;

import com.papio.common.enums.ExceptionEnum;
import lombok.Data;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 14:51 2018/12/30
 * @Modified By:
 **/
@Data
public class ExceptionResult {
    private int status;
    private String message;
    private Long timestamp;

    public ExceptionResult(ExceptionEnum exceptionEnum) {
        this.status=exceptionEnum.getCode();
        this.message=exceptionEnum.getMsg();
        this.timestamp=System.currentTimeMillis();
    }
}
