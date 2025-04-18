package com.hughes.core.aop;


import com.hughes.core.consts.MetaDataTypeEnum;

import java.lang.annotation.*;

/**
 * @author cynic
 * @Description: token校验注解  限用于controller类的方法上;
 *
 * value - 如果token在请求头中(默认),value直接使用默认值即可,其他传参方式,则需要修改value值,参考枚举类com.fh.iasp.app.cuxiao.enums.MetaDataTypeEnum
 * tokenVariableName - 默认token参数名tokenDup,如需自定义,可自己定义该属性值;
 * msgReturnType  -  默认响应体结构 ApiResponse ,  根据方法响应方式,可调整为Response_Plaintext
 * duration  -   锁时长 单位seconds  默认值30
 *
 * @date 2021-06-17
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenValidate {

    MetaDataTypeEnum value() default MetaDataTypeEnum.HEADER; // 默认将防重复校验token放在header中

    String tokenVariableName() default "tokenDup"; //token 参数名 可自定义

    String msgReturnType() default "1"; //返回值响应类型  暂支持ApiResponse / Response_Plaintext

    long duration() default 30l;   //锁时长 单位seconds

}

