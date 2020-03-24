package com.papio.common.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 16:08 2020/2/11
 * @Modified By:
 **/
@Data
@Builder
public class ResponseResult<T> {
    private Integer status;
    private String message;
    private T data;
}
