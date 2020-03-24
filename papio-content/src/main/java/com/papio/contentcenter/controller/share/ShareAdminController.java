package com.papio.contentcenter.controller.share;

import com.papio.contentcenter.domain.dto.share.ShareAuditDTO;
import com.papio.contentcenter.service.share.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 20:23 2020/2/12
 * @Modified By:
 **/
@RestController
@RequestMapping("admin/shares")
public class ShareAdminController {
    @Autowired
    private ShareService shareService;

    /****
     * 根据id修改分享信息
     * @param id
     * @param shareAuditDTO
     * @return
     */
    @PutMapping("auditById/{id}")
    public ResponseEntity auditById(@PathVariable Integer id,@RequestBody ShareAuditDTO shareAuditDTO) {
        return shareService.auditById(id, shareAuditDTO);
    }
}
