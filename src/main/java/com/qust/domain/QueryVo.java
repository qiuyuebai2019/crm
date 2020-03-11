package com.qust.domain;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年05月27日 21时02分
 */
public class QueryVo {

    private int page;

    private int rows;

    private int start;

    private String keyword;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "QueryVo{" + "page=" + page + ", rows=" + rows + ", start=" + start + ", keyword='" + keyword + '\'' + '}';
    }
}
