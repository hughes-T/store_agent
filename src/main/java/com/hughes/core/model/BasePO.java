package com.hughes.core.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.hughes.core.consts.GlobalConstants;
import com.hughes.core.user.UserManger;
import com.hughes.core.utils.StringUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hts
 * @date 2025/4/18 14:26
 */
@Data
public class BasePO implements Serializable {

    @JSONField(serializeUsing = ToStringSerializer.class, serialzeFeatures = {SerializerFeature.WriteMapNullValue})
    private Long id;

    private String status;

    @JSONField(serializeUsing = ToStringSerializer.class, serialzeFeatures = {SerializerFeature.WriteMapNullValue})
    private Long creatorId;

    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date createTime;

    @JSONField(serializeUsing = ToStringSerializer.class, serialzeFeatures = {SerializerFeature.WriteMapNullValue})
    private Long modifyierId;

    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date modifyTime;

    public void init() {
        Long loginUserId = UserManger.getLocalUserId();
        Date date = new Date();
        id = StringUtil.getUUID2Long();
        status = GlobalConstants.STATUS_YES;
        creatorId = loginUserId;
        modifyierId = loginUserId;
        createTime = date;
        modifyTime = date;
    }

    public void mod() {
        modifyierId = UserManger.getLocalUserId();
        modifyTime = new Date();
    }

}
