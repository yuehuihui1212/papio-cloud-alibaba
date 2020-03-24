package com.papio.common.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 16:01 2018/12/23
 * @Modified By:
 **/
@Data
public class PageResult<T>{
    /**
     * 总条数
     */
    private Long total;

    /**
     * 总页数
     */
    private Long totalPage;

    /**
     * 当前页数据
     */
    private List<T> items;

    public PageResult(long total, List<T> list) {
        this.total=total;
        this.items=list;
    }
}
