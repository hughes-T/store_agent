package com.hughes.core.aop;


import com.hughes.core.cache.RedisUtils;
import com.hughes.core.consts.GlobalConstants;
import com.hughes.core.consts.MetaDataTypeEnum;
import com.hughes.core.exceptions.BizException;
import com.hughes.core.exceptions.DefaultExceptionHandler;
import com.hughes.core.model.ApiResponse;
import com.hughes.core.utils.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author cynic
 * @Description: token validate aspect
 * @date 2021-06-17
 */
@Aspect
@Component
public class TokenValidateAspect {

    private static final Logger logger = LoggerFactory.getLogger(TokenValidateAspect.class);

    private static final String TOKEN_KEY_TEMPLATE = "token:validate:%s";


    @Resource
    private RedisUtils redisUtils;

    @Pointcut("@annotation(com.hughes.core.aop.TokenValidate)")
    public void validateToken() {
    }


    @Around(value = "validateToken() && @annotation(tokenValidate)")
    public Object around(ProceedingJoinPoint pjp, TokenValidate tokenValidate) throws Throwable {
        String token = getToken(tokenValidate, pjp.getArgs());
        if (StringUtil.isEmpty(token)) {
            //因客户端版本问题,不传token,默认略过
            return pjp.proceed();
        }
        logger.info("防重复token校验 token={}", token);
        String keyStr = String.format(TOKEN_KEY_TEMPLATE, token);
        String lockToken = "2333";

        //加锁时间取注解自定义属性,默认30s
//        String lockToken = redisComplexLock.tryLock(keyStr, tokenValidate.duration(), TimeUnit.SECONDS);
        Boolean lockSuccess = redisUtils.setIfAbsent(keyStr, GlobalConstants.STATUS_YES, tokenValidate.duration(), TimeUnit.SECONDS);

        if (!lockSuccess) {
            logger.info("发生重复提交,token:{}", token);
            return msgErrorReturnRepeatToken("请勿重复提交请求", tokenValidate.msgReturnType());
        }
        Object obj;
        try {
            obj = pjp.proceed();
        } catch (Exception e) {
            //使用自定义异常处理器时,抛出此类业务异常;如已在业务自行处理,也不会进到此处代码;  如使用@tokenValidate注解的同时包含其他自定义异常处理,请在此处同步扩展
            if (e instanceof BizException || e instanceof IllegalArgumentException ) {
                throw e;
            }
            // log
            logger.error("产生未捕获异常,", e);
            //报错情况下 返回该实体  但实际上要根据返回类型做具体处理
            return msgErrorReturn(DefaultExceptionHandler.MSG_ERROR, tokenValidate.msgReturnType());
        } finally {
            //释放token锁
//            if (StringUtil.isNotEmpty(lockToken)) {
//                redisComplexLock.releaseLock(keyStr, lockToken);
//            }
        }
        return obj;
    }

    private String getToken(TokenValidate tokenValidate, Object[] args) {
        String token = "";
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        //如果token放在请求头中
        if (tokenValidate.value().equals(MetaDataTypeEnum.HEADER)) {
            token = request.getHeader(tokenValidate.tokenVariableName());
        } else if (tokenValidate.value().equals(MetaDataTypeEnum.MAIN_BODY)) {
            token = request.getParameter(tokenValidate.tokenVariableName());
        } else {
            if (!(args == null || args.length == 0)) {
                for (Object var1 : args) {
                    if (var1 instanceof HashMap) {
                        token = (String) ((HashMap) var1).get(tokenValidate.tokenVariableName());
                        if (StringUtil.isNotEmpty(token)) {
                            break;
                        }
                    } else {
                        boolean hasTokenValue = false;
                        // 得到类对象
                        Class clazz = var1.getClass();
                        //得到类中的所有属性集合
                        Field[] fs = clazz.getDeclaredFields();
                        for (int i = 0; i < fs.length; i++) {
                            Field f = fs[i];
                            f.setAccessible(true); // 设置些属性是可以访问的
                            if (tokenValidate.tokenVariableName().equals(f.getName())) {
                                try {
                                    token = (String) f.get(var1);
                                } catch (Exception e) {
                                    //ignore
                                }
                                hasTokenValue = true;
                                break;
                            }
                        }
                        if (hasTokenValue) {
                            break;
                        }
                    }
                }
            }
        }
        return token;
    }

    private Object msgErrorReturn(String msg, String responseType) {
        if (GlobalConstants.API_RESPONSE.equals(responseType)) {
            return ApiResponse.failed(ApiResponse.CODE_FAIL_DEFAULT, msg);
        } else if (GlobalConstants.RESPONSE_PLAINTEXT.equals(responseType)) {
//            ActionUtil.responsePlainText(JsonUtil.getErrMsg(msg));
            return null;
        }
        return ApiResponse.failed(ApiResponse.CODE_FAIL_DEFAULT, msg);
    }

    private Object msgErrorReturnRepeatToken(String msg, String responseType) {
        if (GlobalConstants.API_RESPONSE.equals(responseType)) {
            return ApiResponse.failed(GlobalConstants.TOKEN_REPEAT_CODE, msg);
        } else if (GlobalConstants.RESPONSE_PLAINTEXT.equals(responseType)) {
            //ActionUtil.responsePlainText(JsonUtil.fastReturn(JsonUtil.CODE_BIZ, msg).toJSONString());
            return null;
        }
        return ApiResponse.failed(GlobalConstants.TOKEN_REPEAT_CODE, msg);
    }


}
