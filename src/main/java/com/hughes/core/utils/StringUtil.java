package com.hughes.core.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @author hts
 * @date 2025/4/18 14:30
 */
public class StringUtil {

    /**
     * 获取Long型的UUID（唯一）.
     *
     * @return the UUID least bits
     */
    public static long getUUID2Long()
    {
        return java.util.UUID.randomUUID().getLeastSignificantBits() * -1;
    }


    /**
     * 检查对象是否为空，对象主要用于判断字符串是否为空，同时兼容集合、哈希表
     *
     * @param str
     *            the str
     * @return true, if is empty
     */
    public static boolean isEmpty(Object str)
    {
        if(str == null)
        {
            return true;
        }
        if(str instanceof Collection)
        {
            Collection col = (Collection) str;
            return col.isEmpty();
        }
        else if(str instanceof Map)
        {
            Map map = (Map) str;
            return map.isEmpty();
        }
        return str.toString().isEmpty();
    }

    /**
     * Checks if is not empty.
     *
     * @param str
     *            the str
     * @return true, if is not empty
     */
    public static boolean isNotEmpty(Object str)
    {
        return !StringUtil.isEmpty(str);
    }
}
