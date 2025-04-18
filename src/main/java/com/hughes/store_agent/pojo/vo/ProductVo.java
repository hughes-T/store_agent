package com.hughes.store_agent.pojo.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.Data;

/**
 * @author hts
 * @date 2025/4/18 16:50
 */
@Data
public class ProductVo {

    @JSONField(serializeUsing = ToStringSerializer.class, serialzeFeatures = {SerializerFeature.WriteMapNullValue})
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品分类
     */
    @JSONField(serializeUsing = ToStringSerializer.class, serialzeFeatures = {SerializerFeature.WriteMapNullValue})
    private Long typeId;

    /**
     * 商品分类名称
     */
    private String typeName;

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

    /**
     * 审批状态 1-通过 2-未通过 3-审核中 9-草稿
     */
    private String flowStatus;

    /**
     * 审批备注
     */
    private String flowRemark;


}
