package com.papio.contentcenter.domain.dto.share;

import com.papio.contentcenter.domain.entity.content.Share;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 16:59 2020/2/6
 * @Modified By:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ShareDTO extends Share {
    /**
     * 微信昵称
     */
    private String wxNickname;
}
