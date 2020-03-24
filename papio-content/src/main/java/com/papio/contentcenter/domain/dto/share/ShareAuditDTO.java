package com.papio.contentcenter.domain.dto.share;

import com.papio.contentcenter.domain.enums.AuditStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 18:53 2020/2/12
 * @Modified By:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShareAuditDTO {
    /**
     * 审核状态
     */
    private AuditStatusEnum auditStatusEnum;
    /**
     * 原因
     */
    private String reason;
}
