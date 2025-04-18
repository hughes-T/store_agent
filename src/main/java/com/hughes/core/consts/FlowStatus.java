package com.hughes.core.consts;

import lombok.Getter;

/**
 * @author hts
 * @date 2025/4/18 15:19
 */
@Getter
public enum FlowStatus {

    FLOW_STATUS_FINISH_AUDIT("1", "通过"),
    FLOW_STATUS_REJECT("2", "未通过"),
    FLOW_STATUS_IN_AUDIT("3", "审核中"),
    FLOW_STATUS_WAIT_FOR_SUMMIT("9", "草稿");


    FlowStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private final String code;
    private final String desc;

}
