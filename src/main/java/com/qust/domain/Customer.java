package com.qust.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年06月08日 09时05分
 */
public class Customer {

    private Long id;

    private String cusName;

    private String cusTel;

    private String cusAddress;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cusVisitTime;

    private Long cusEmployee;

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusTel() {
        return cusTel;
    }

    public void setCusTel(String cusTel) {
        this.cusTel = cusTel;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public Date getCusVisitTime() {
        return cusVisitTime;
    }

    public void setCusVisitTime(Date cusVisitTime) {
        this.cusVisitTime = cusVisitTime;
    }

    public Long getCusEmployee() {
        return cusEmployee;
    }

    public void setCusEmployee(Long cusEmployee) {
        this.cusEmployee = cusEmployee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", cusName='" + cusName + '\'' + ", cusTel='" + cusTel + '\'' + ", cusAddress='" + cusAddress + '\'' + ", cusVisitTime='" + cusVisitTime + '\'' + ", cusEmployee=" + cusEmployee + '}';
    }
}
