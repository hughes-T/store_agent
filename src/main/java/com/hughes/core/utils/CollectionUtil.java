package com.hughes.core.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @author hts
 * @date 2025/4/18 15:59
 */
public class CollectionUtil {

    /**
     * Checks if is empty.
     *
     * @param collection
     *            the collection
     *
     * @return true, if is empty
     */
    public static boolean isEmpty(Collection<?> collection)
    {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断是否非空
     *
     * @param collection
     *            集合
     * @return isNotEmpty
     */
    /**
     * Checks if is not empty.
     *
     * @param collection
     *            the collection
     *
     * @return true, if is not empty
     */
    public static boolean isNotEmpty(Collection<?> collection)
    {
        return !isEmpty(collection);
    }

    /**
     * Checks if is empty.
     *
     * @param map
     *            the map
     *
     * @return true, if is empty
     */
    public static boolean isEmpty(Map<?, ?> map)
    {
        return map == null || map.isEmpty();
    }

}
