package com.papio.contentcenter.config.sentinel;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author: yuehuihui
 * @Description:自定义restful API限流
 * @Date: created in 17:06 2020/2/11
 * @Modified By:
 **/
@Component
@Slf4j
public class PapioUrlCleaner implements UrlCleaner {
    @Override
    public String clean(String s) {
        String[] split = s.split("/");
        String url = Arrays.stream(split)
                .map(string -> {
                    if (NumberUtils.isDigits(string)) {
                        return "{number}";
                    }
                    return string;
                })
                .reduce((a, b) -> a + "/" + b)
                .orElse("");
        log.info("url -> {}",url);
        return url;
    }
}
