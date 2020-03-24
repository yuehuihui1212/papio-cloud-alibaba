package com.papio.contentcenter.service.share;

import com.papio.contentcenter.domain.dto.share.ShareAuditDTO;
import com.papio.contentcenter.domain.dto.share.ShareDTO;
import com.papio.contentcenter.domain.dto.user.UserDTO;
import org.springframework.http.ResponseEntity;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 16:56 2020/2/6
 * @Modified By:
 **/
public interface ShareService {
    /**
     * 根据分享id查询分享信息
     * @param id
     * @return
     */
    ShareDTO queryShareById(Integer id);

    UserDTO queryUser(UserDTO userDTO);

    /***
     * 根据id修改分享信息
     * @param id
     * @param shareAuditDTO
     * @return
     */
    ResponseEntity auditById(Integer id, ShareAuditDTO shareAuditDTO);

    void auditShareByIdWithMqTxLog(Integer id, ShareAuditDTO shareAuditDTO, String transactionId);
}
