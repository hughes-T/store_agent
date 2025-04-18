package com.hughes.store_agent.pojo.dto;

import lombok.Data;

/**
 * @author hts
 * @date 2025/4/18 15:15
 */
@Data
public class ProductDTO {

    private Long id;

    /**
     * 审批状态 1-通过 2-未通过 3-审核中 9-草稿
     */
    private String flowStatus;

    /**
     * 审批备注
     */
    private String flowRemark;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品分类
     */
    private Long typeId;

    /**
     * 商品品牌
     */
    private String brand;

    /**
     * 商品规格
     */
    private String spec;

    /**
     * 规格单位
     */
    private String specUnit;

    /**
     * 零售规格条形码
     */
    private String barCode;

    /**
     * 商品属性 1-普通商品
     */
    private String propType;

    /**
     * 商品图片
     */
    private String picPath;



}
