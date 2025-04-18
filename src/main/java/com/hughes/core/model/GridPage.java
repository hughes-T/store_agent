package com.hughes.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cynic
 * @version 1.0
 * @description: grid数据响应体
 * @date 2021/9/13 19:47
 */
public class GridPage<T, V> {

    private int total;
    private List<T> rows;
    private List<V> footer;

    public GridPage() {
    }

    public GridPage(int total) {
        this.total = total;
    }

    public static GridPage ofNoCount() {
        return new GridPage(-1);
    }

    public static GridPage ofTotal(int total) {
        return new GridPage(total);
    }

    public static GridPage ofEmpty() {
        return new GridPage(0);
    }


    public boolean ifCounts() {
        if (this.total > 0) {
            return true;
        } else {
            this.setRows(new ArrayList());
            return false;
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public List<V> getFooter() {
        return footer;
    }

    public void setFooter(List<V> footer) {
        this.footer = footer;
    }
}
