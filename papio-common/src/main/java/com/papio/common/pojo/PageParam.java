package com.papio.common.pojo;

import lombok.Data;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 16:08 2018/12/23
 * @Modified By:
 **/
@Data
public class PageParam {
    /**
     * 当前页
     */
    private Integer page;
    /**
     * 每页显示的条数
     */
    private Integer rows;
    /**
     * 排序字段
     */
    private String sortBy;
    /**
     * 是否是降序排序
     */
    private boolean desc;
    /**
     * 搜索关键字
     */
    private String key;
}
