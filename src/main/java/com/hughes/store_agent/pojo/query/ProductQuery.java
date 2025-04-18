package com.hughes.store_agent.pojo.query;

import com.hughes.core.model.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author hts
 * @date 2025/4/18 16:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductQuery extends PageParam {

    private Long id;

    private String flowStatus;

    private Date startDate;

    private Date endDate;

    /**
     * 快捷模糊搜索 如商品名称
     */
    private String searchKey;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 零售规格条形码
     */
    private String barCode;


}
