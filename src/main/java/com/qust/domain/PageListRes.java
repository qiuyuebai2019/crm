package com.qust.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年05月27日 13时39分
 */
public class PageListRes {

    private Integer total;

    private List<?> rows = new ArrayList<>();

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageListRes{" + "total=" + total + ", rows=" + rows + '}';
    }
}
