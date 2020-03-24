package com.papio.common.exception;

import com.papio.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 18:49 2019/7/25
 * @Modified By:
 **/
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PapioException extends RuntimeException{
    private ExceptionEnum exceptionEnum;
}
