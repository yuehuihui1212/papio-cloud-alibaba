package com.papio.contentcenter.config.sentinel;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.papio.common.pojo.ResponseResult;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: yuehuihui
 * @Description:自定义sentinel流控、降级、系统等规则返回异常信息
 * @Date: created in 16:12 2020/2/11
 * @Modified By:
 **/
@Component
public class PapioUrlBlockHandler implements UrlBlockHandler {
    @Override
    public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {
        ResponseResult<Object> data=null;
        if (e instanceof FlowException) {
             data = ResponseResult.builder()
                     .status(100)
                     .message("限流了")
                     .build();
        } else if (e instanceof DegradeException) {
            data = ResponseResult.builder()
                    .status(101)
                    .message("降级了")
                    .build();
        } else if (e instanceof ParamFlowException) {
            data = ResponseResult.builder()
                    .status(102)
                    .message("热点参数限流")
                    .build();
        } else if (e instanceof SystemBlockException) {
            data = ResponseResult.builder()
                    .status(103)
                    .message("系统规则(负载/..不满足要求)")
                    .build();
        } else if (e instanceof AuthorityException) {
            data = ResponseResult.builder()
                    .status(104)
                    .message("授权规则不通过")
                    .build();
        }
        httpServletResponse.setStatus(500);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setHeader("ContentType","application/json;charset=utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        new ObjectMapper()
                .writeValue(httpServletResponse.getWriter(),data);
    }
}
