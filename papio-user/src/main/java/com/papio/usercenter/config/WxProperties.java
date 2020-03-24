package com.papio.usercenter.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ConfigurationProperties(prefix = "wx")
public class WxProperties {
    /**
     * 微信公众号appid
     */
    private String appId;
    /**
     * appid 对应密钥
     */
    private String secret;
}
