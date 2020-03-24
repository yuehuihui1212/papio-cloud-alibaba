package com.papio.usercenter.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtTokenRespDTO {
    /**
     * token
     */
    private String token;

    /**
     * 过时时间
     */
    private Long expireTime;
}
