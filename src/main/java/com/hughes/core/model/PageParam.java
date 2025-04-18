package com.hughes.core.model;

import lombok.Data;

/**
 * @Description 分页参数
 * @Author hanyu
 * @Date 2022/2/22 17:00
 */
@Data
public class PageParam {

    private Integer page = 1;

    private Integer rows = 100;

    private Integer offset;

    private Integer limit;

    public void setPageParam() {
        this.limit = rows;
        this.offset = (page - 1)*rows;
    }

    public void clearPageParam() {
        this.limit = null;
        this.offset = null;
    }
}
