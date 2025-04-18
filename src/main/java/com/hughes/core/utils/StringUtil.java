package com.hughes.core.utils;

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

}
