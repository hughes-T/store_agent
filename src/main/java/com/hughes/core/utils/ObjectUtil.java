package com.hughes.core.utils;

import com.google.common.collect.Lists;
import com.hughes.core.exceptions.BizException;
import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author hts
 * @date 2025/4/18 15:27
 */
@Slf4j
public class ObjectUtil {

    /**
     * 检查参数（value）是否为空
     *
     * @param value
     *            检查值
     * @return true:是，false:否
     */
    public static boolean isEmpty(Object value)
    {
        return value == null || (value.toString().isEmpty());
    }

    public static boolean isNotEmpty(Object value)
    {
        return !isEmpty(value);
    }

    /**
     * 实体类转化
     * 相同属性且有 get、set 方法时，会被赋值
     */
    public static <T, R> R beanConvert(T target, Class<R> rClass) {
        try {
            R result = rClass.newInstance();
            BeanInfo tBeanInfo = Introspector.getBeanInfo(target.getClass());
            BeanInfo rBeanInfo = Introspector.getBeanInfo(rClass);
            PropertyDescriptor[] tProperties = tBeanInfo.getPropertyDescriptors();
            PropertyDescriptor[] rProperties = rBeanInfo.getPropertyDescriptors();
            for (PropertyDescriptor tProperty : tProperties) {
                for (PropertyDescriptor rProperty : rProperties) {
                    if (tProperty.getName().equals(rProperty.getName()) && tProperty.getPropertyType() == rProperty.getPropertyType()) {
                        Object value = tProperty.getReadMethod().invoke(target);
                        Method writeMethod = rProperty.getWriteMethod();
                        if (writeMethod != null) {
                            writeMethod.invoke(result, value);
                        }
                    }
                }
            }
            return result;
        } catch (Exception e) {
            log.error("实体类转换异常", e);
            throw new BizException(0, "实体类转化异常");
        }
    }

    public static <T, R> List<R> beanConvert(List<T> target, Class<R> rClass) {
        List<R> results = Lists.newArrayList();
        if (CollectionUtil.isEmpty(target)) {
            return results;
        }
        for (T t : target) {
            results.add(beanConvert(t, rClass));
        }
        return results;
    }

    /**
     * 实体类转化 - map 版
     */
    public static <R> R beanConvert(Map<String, Object> target, Class<R> rClass) {
        try {
            R result = rClass.newInstance();
            BeanInfo rBeanInfo = Introspector.getBeanInfo(rClass);
            PropertyDescriptor[] rProperties = rBeanInfo.getPropertyDescriptors();
            for (PropertyDescriptor rProperty : rProperties) {
                String propertyName = rProperty.getName();
                Object value = target.get(propertyName);
                if (value != null) {
                    Method writeMethod = rProperty.getWriteMethod();
                    if (writeMethod != null && writeMethod.getParameterTypes().length > 0 &&
                            writeMethod.getParameterTypes()[0].isAssignableFrom(value.getClass())) {
                        writeMethod.invoke(result, value);
                    }
                }
            }
            return result;
        } catch (Exception e) {
            log.error("实体类转换异常", e);
            throw new BizException(0, "实体类转化异常");
        }
    }


    public static <R> List<R> beanConvertList(List<Map<String, Object>> targetList, Class<R> rClass) {
        List<R> resultList = Lists.newArrayList();
        for (Map<String, Object> target : targetList) {
            resultList.add(beanConvert(target, rClass));
        }
        return resultList;
    }

}
