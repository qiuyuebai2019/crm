package com.qust.domain;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年05月27日 21时59分
 */
public class Permission {

    private Long pid;

    private String pname;

    private String presource;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPresource() {
        return presource;
    }

    public void setPresource(String presource) {
        this.presource = presource;
    }

    @Override
    public String toString() {
        return "Permission{" + "pid=" + pid + ", pname='" + pname + '\'' + ", presource='" + presource + '\'' + '}';
    }
}
