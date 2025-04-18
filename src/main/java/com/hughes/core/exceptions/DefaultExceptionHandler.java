package com.hughes.core.exceptions;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.hughes.core.model.ApiResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cynic
 * @version 1.0
 * @description: 通用异常处理器 - 实现该接口可无需在业务接口做异常细分处理;
 * @date 2021/7/26 16:39
 */
public interface DefaultExceptionHandler {

    Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    String MSG_ERROR = "服务器繁忙,请稍后重试";

    /**
     * 默认的异常处理器
     *
     * @param e
     * @return org.springframework.web.servlet.ModelAndView
     */
    @ExceptionHandler(Exception.class)
    default ModelAndView exceptionHandler(Exception e) {
        String msg = MSG_ERROR;
        //业务异常,正常响应业务异常消息;
        if (e instanceof BizException || e instanceof IllegalArgumentException) {
            msg = e.getMessage();
        } else {
            logger.error("发生非预期异常:{}", ExceptionUtils.getStackTrace(e));
        }
        ModelAndView modelAndView = new ModelAndView();
        FastJsonJsonView jsonView = new FastJsonJsonView();
        Map<String, Object> errorInfoMap = new HashMap<>();
        errorInfoMap.put("code", ApiResponse.CODE_FAIL_DEFAULT);
        errorInfoMap.put("message", msg);
        errorInfoMap.put("success", false);
        errorInfoMap.put("data", "");
        jsonView.setAttributesMap(errorInfoMap);
        modelAndView.setView(jsonView);
        return modelAndView;
    }

}
