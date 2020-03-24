package com.papio;

import com.papio.contentcenter.domain.enums.AuditStatusEnum;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 17:41 2020/2/13
 * @Modified By:
 **/
public class Test {
    public static void main(String[] args) {
        String name = AuditStatusEnum.NOT_YET.name().toString();
        System.out.println(name);
    }
}
