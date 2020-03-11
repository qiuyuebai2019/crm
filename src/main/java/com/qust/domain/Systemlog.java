package com.qust.domain;

import java.util.Date;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年06月03日 09时44分
 */
public class Systemlog {

    private Long id;

    private Date optime;

    private String ip;

    private String function;

    private String params;

    private Long userid;

    public Date getOptime() {
        return optime;
    }

    public void setOptime(Date optime) {
        this.optime = optime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Systemlog{" + "id=" + id + ", optime=" + optime + ", ip='" + ip + '\'' + ", function='" + function + '\'' + ", params='" + params + '\'' + ", userid=" + userid + '}';
    }
}
