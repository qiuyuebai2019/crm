package com.qust.domain;

import java.util.List;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年05月27日 21时55分
 */
public class Role {

    private Long rid;

    private String rnum;

    private String rname;

    private List<Permission> permissions;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getRnum() {
        return rnum;
    }

    public void setRnum(String rnum) {
        this.rnum = rnum;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Role{" + "rid=" + rid + ", rnum='" + rnum + '\'' + ", rname='" + rname + '\'' + ", permissions=" + permissions + '}';
    }
}
