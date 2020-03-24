package com.papio.common.advice;

import com.papio.common.exception.PapioException;
import com.papio.common.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 14:33 2018/12/30
 * @Modified By:
 **/
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(PapioException.class)
    public ResponseEntity<ExceptionResult> handleException(PapioException rs) {
        return ResponseEntity.status(rs.getExceptionEnum().getCode())
                .body(new ExceptionResult(rs.getExceptionEnum()));
    }

}
