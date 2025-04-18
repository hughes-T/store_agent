package com.hughes.core.exceptions;

import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * @author hts
 * @date 2025/4/18 11:07
 */
@Getter
public class BizException extends RuntimeException  {

    private static final long serialVersionUID = -7924878864085184620L;
    private int errorCode;


    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(int errorCode, String message) {
        super(generateMessageWithCodeAndMsg(errorCode, message));
        this.errorCode = errorCode;
    }

    public BizException(int errorCode, Throwable cause) {
        super(generateMessageWithCode(errorCode), cause);
        this.errorCode = errorCode;
    }


    private static String generateMessageWithCodeAndMsg(int code, String message) {
        return !StringUtils.isEmpty(message) ? message : "error code not init yet!errorCode=" + code;
    }

    private static String generateMessageWithCode(int code) {
        return generateMessageWithCodeAndMsg(code, null);
    }



}
