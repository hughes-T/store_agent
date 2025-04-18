package com.hughes.core.consts;

/**
 * @author hts
 * @date 2025/4/18 14:28
 */
public interface GlobalConstants {

    String STATUS_YES = "1";

    String STATUS_NO = "0";

    /**
     * 审批状态 1-通过 2-未通过 3-审核中 9-草稿
     */
    String FLOW_STATUS_FINISH_AUDIT = "1";
    String FLOW_STATUS_REJECT = "2";
    String FLOW_STATUS_IN_AUDIT = "3";
    String FLOW_STATUS_WAIT_FOR_SUMMIT = "9";


    /**
     * 响应类型  1-ApiResponse 2-Response_Plaintext
     */
    String API_RESPONSE = "1";
    String RESPONSE_PLAINTEXT = "2";

    String TOKEN_REPEAT_CODE = "2";

}
