package com.papio.contentcenter.config.sentinel;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: yuehuihui
 * @Description:实现sentinel区分应用来源用于流控
 * @Date: created in 16:54 2020/2/11
 * @Modified By:
 **/

public class PapioRequestOriginParser implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        String origin = httpServletRequest.getParameter("origin");
        if (StringUtils.isEmpty(origin)) {
            throw new IllegalArgumentException("origin must be specified.");
        }
        return origin;
    }
}
